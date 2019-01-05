/*
 * Copyright (C) 2018  Ľuboš Kozmon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package serialization.json.api

import api.ApiResponse
import org.scalatest.FlatSpec
import play.api.libs.json._

import scala.language.postfixOps

@SuppressWarnings(Array("org.wartremover.warts.JsLookupResultPartial"))
class JsonApiResponseSpec extends FlatSpec with JsonApiResponse {

  "Serialized JsonApiResponse" should "be a JSON object with 'success' boolean field of value JsTrue" in {
    val a = ApiResponse(success = true, Some("foo"), Some(123))
    val j = implicitly[Writes[a.type]].writes(a)

    assertResult(JsTrue)(j \ "success" get)
  }

  it should "be a JSON object with 'message' field of value 'foo'" in {
    val a = ApiResponse(success = true, Some("foo"), Some(123))
    val j = implicitly[Writes[a.type]].writes(a)

    assertResult(JsString("foo"))(j \ "message" get)
  }

  it should "be a JSON object with 'payload' field of value 123" in {
    val a = ApiResponse(success = true, Some("foo"), Some(123))
    val j = implicitly[Writes[a.type]].writes(a)

    assertResult(JsNumber(123))(j \ "payload" get)
  }
}