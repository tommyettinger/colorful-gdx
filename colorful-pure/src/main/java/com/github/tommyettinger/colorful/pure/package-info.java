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
 * FloatColors has methods that work with almost any color space. The Interpolations class is deprecated here; it is
 * mostly copied from libGDX and provides code to adjust the curve of interpolations, but is complete superseded by the
 * Interpolations class from the digital library. The MathTools class here is also deprecated in favor of the
 * more-full-featured MathTools and TrigTools classes in the digital library.
 */
package com.github.tommyettinger.colorful.pure;