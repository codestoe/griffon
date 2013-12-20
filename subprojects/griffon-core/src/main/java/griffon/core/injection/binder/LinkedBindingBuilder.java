/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.core.injection.binder;

import javax.annotation.Nonnull;
import javax.inject.Provider;

/**
 * @author Andres Almiray
 * @since 2.0.0
 */
public interface LinkedBindingBuilder<T> extends SingletonBindingBuilder<T> {
    @Nonnull
    SingletonBindingBuilder<T> to(@Nonnull Class<? extends T> target);

    void toInstance(@Nonnull T instance);

    @Nonnull
    SingletonBindingBuilder<T> toProvider(@Nonnull Provider<? extends T> provider);

    @Nonnull
    SingletonBindingBuilder<T> toProvider(@Nonnull Class<Provider<? extends T>> providerType);
}
