/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Various utility classes for handling any of the color spaces, which are in subpackages of this one.
 * FloatColors provides various methods that work for almost any color space, TrigTools provides the basic trigonometric
 * approximations that lots of code here uses, and Shaders is a big repository of GLSL shader code meant to be used with
 * SpriteBatch.
 */
package com.github.tommyettinger.colorful;