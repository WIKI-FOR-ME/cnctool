package jde.com

import java.io.{File, FileInputStream, FileOutputStream, PrintWriter}
import java.sql.{DriverManager, Connection}
import java.util.Properties

/**
  * Created by ashish on 13/05/2016.
  */
class daddriver {

  def connect (Sql:String): Any = {
    val prop = new Properties();
    val conFile = new FileInputStream("./config/config.properties")
    prop.load(conFile)
    val url= prop.getProperty("url")
    val username=prop.getProperty("username")
    val password=prop.getProperty("password")
    Class.forName(prop.getProperty("classname"));
    var connection:Connection = null
    try{
    connection = DriverManager.getConnection( url, username, password);
    val Statments = connection.prepareStatement(Sql);
    val resultset = Statments.executeQuery()
    val  resultmetadata = resultset.getMetaData();
    val numCols = resultmetadata.getColumnCount();
    val outFileName ="./output/out.html"
    val f= new File(outFileName)
    var outfile:PrintWriter= null
    if (f.exists() && !f.isDirectory() )
      outfile = new PrintWriter(new FileOutputStream( new File(outFileName),true ), true )
    else
      outfile = new PrintWriter(new FileOutputStream( new File(outFileName) ) )
    outfile.write("<table border=1>")
    outfile.write("<tr>")
    for (i <- 1 to numCols) {
      outfile.write("<th>")
      outfile.write(resultmetadata.getColumnLabel(i))
    }
    outfile.write("</tr>")
    while (resultset.next()) {
      outfile.write("</tr>")
      for (i <- 1 to numCols) {
        outfile.write("<td>")
        outfile.write(resultset.getString(i))
      }
      outfile.write("</tr>")
    }
    outfile.write("</table>")
    outfile.close()
    connection.close()
  }
  catch {
    case ex:ClassNotFoundException =>{
      println ("Classnot found")
    }
    case ex:NullPointerException=>{
      println ("Connection Error")
    }
    case ex:java.sql.SQLException=>{
      println ("SQL exception")

    }
  }

  }

}
