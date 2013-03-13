## Play-JSON from Playframework 2.2-SNAPSHOT

This is just a copy-paste backport of JSON macros from Play 2.2-SNAPSHOT. Version 2.1 has some issues with companion objects for your case classes. This is interopable with 2.1's JSON framework. 

### Usage 
Build configuration
    
    resolvers += "edofic snapshots" at "http://edofic.github.com/repository/snapshots"
    libraryDependencies += "com.edofic" %% "play22-json-backport" % "0.1-SNAPSHOT"

In code

    import play.api.libs.json.Json22 // ...json._ will work too
    
    case class Foo(bar:String)
    object Foo{
      //this now compiles without any hackery
      implicit val f = Json22.format[Foo] 
    }


