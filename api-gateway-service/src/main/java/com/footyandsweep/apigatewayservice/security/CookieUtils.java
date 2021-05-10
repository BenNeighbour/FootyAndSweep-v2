/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.footyandsweep.apigatewayservice.security;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.Optional;

public class CookieUtils {

    public static Optional<String> getTokenFromCookie(ServerHttpRequest request) {
        /* Find the cookie with the name of token */
        List<HttpCookie> cookie = request.getCookies().get("token");

        if (cookie == null || cookie.isEmpty() || cookie.get(0) == null) return Optional.empty();

        return Optional.of(cookie.get(0).getValue());
    }

}
