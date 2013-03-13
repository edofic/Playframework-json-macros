package play.api.libs.json

import scala.language.reflectiveCalls

/**
 * Backport of some changes in Play 2.2
 */
object Json22 {
  import scala.reflect.macros.Context
  import language.experimental.macros

  /**
   * Creates a Reads[T] by resolving case class fields & required implcits at COMPILE-time.
   *
   * If any missing implicit is discovered, compiler will break with corresponding error.
   * {{{
   *   import play.api.libs.json.Json
   *
   *   case class User(name: String, age: Int)
   *
   *   implicit val userReads = Json.reads[User]
   *   // macro-compiler replaces Json.reads[User] by injecting into compile chain 
   *   // the exact code you would write yourself. This is strictly equivalent to:
   *   implicit val userReads = (
   *      (__ \ 'name).read[String] and
   *      (__ \ 'age).read[Int]
   *   )(User)
   * }}}
   */
  def reads[A] = macro JsMacroImpl22.readsImpl[A]

  /**
   * Creates a Writes[T] by resolving case class fields & required implcits at COMPILE-time
   *
   * If any missing implicit is discovered, compiler will break with corresponding error.
   * {{{
   *   import play.api.libs.json.Json
   * 
   *   case class User(name: String, age: Int)
   *
   *   implicit val userWrites = Json.writes[User]
   *   // macro-compiler replaces Json.writes[User] by injecting into compile chain 
   *   // the exact code you would write yourself. This is strictly equivalent to:
   *   implicit val userWrites = (
   *      (__ \ 'name).write[String] and
   *      (__ \ 'age).write[Int]
   *   )(unlift(User.unapply))
   * }}}
   */
  def writes[A] = macro JsMacroImpl22.writesImpl[A]  

  /**
   * Creates a Format[T] by resolving case class fields & required implicits at COMPILE-time
   *
   * If any missing implicit is discovered, compiler will break with corresponding error.
   * {{{
   *   import play.api.libs.json.Json
   *
   *   case class User(name: String, age: Int)
   *
   *   implicit val userWrites = Json.format[User]
   *   // macro-compiler replaces Json.format[User] by injecting into compile chain 
   *   // the exact code you would write yourself. This is strictly equivalent to:
   *   implicit val userWrites = (
   *      (__ \ 'name).format[String] and
   *      (__ \ 'age).format[Int]
   *   )(User.apply, unlift(User.unapply))
   * }}}
   */
  def format[A] = macro JsMacroImpl22.formatImpl[A]

}
