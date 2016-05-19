package jde.com


/**
  * Created by ashish on 13/05/2016.
  */
object cncmain extends App {
  val dbconnect = new daddriver ;
  val SQLFile = new FileReader("./sqlfiles/")
  val files =SQLFile.getListOfFiles()
  files.foreach(println)
  for ( file <- files){
    val TagTitle = SQLFile.ReadTag( file , "Title")
    val Tag = SQLFile.ReadTag( file , "SQL")
    //logger.info("Executting SQL " + Tag)
    val OutSql=  dbconnect.connect(Tag.substring(1, Tag.length-1))
  }
  println("HelloWorld")
}
