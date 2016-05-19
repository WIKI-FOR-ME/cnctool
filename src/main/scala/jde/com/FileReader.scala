package jde.com
import java.io.File

import scala.util.control.Breaks

/**
  * Created by ashish on 8/03/2016.
  */
class FileReader (fileLoc : String) {
   def this(){
    this("/temp")
      }
  def getListOfFiles():List[File] = {
    //val d = new File(dir)
    val d = new File(fileLoc)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }
def ReadTag (FileNaMe :File, Tag : String)  : String = {
   val src = io.Source.fromFile(FileNaMe)
  val loop = new Breaks;
  var TagValue : String = null ;
  loop.breakable {
  for (line <- src.getLines) {
    val cols = line.split(":").map(_.trim)
       if (cols(0) == Tag){
         TagValue = cols(1)
         src.close()
         loop.break;}
  }
  }
 return TagValue
}
}
