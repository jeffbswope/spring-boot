/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.diagnostics;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.testsupport.runner.classpath.ClassPathExclusions;
import org.springframework.boot.testsupport.runner.classpath.ModifiedClassPathRunner;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test that an {@link AbstractFailureAnalyzer} implementation will not
 * throw when its generic type is not present on the classpath.
 *
 * @author Jeff Swope
 */
@RunWith(ModifiedClassPathRunner.class)
@ClassPathExclusions("spring-jdbc*.jar")
public class AbstractFailureAnalyzerTypeNotPresentTests {

	@Test
	public void test() {
		FailureAnalyzer failureAnalyzer = new TestFailureAnalyzer();

		assertThat(failureAnalyzer.analyze(new RuntimeException())).isNull();
	}

	static class TestFailureAnalyzer
			extends AbstractFailureAnalyzer<CannotGetJdbcConnectionException> {

		@Override
		protected FailureAnalysis analyze(Throwable rootFailure, CannotGetJdbcConnectionException cause) {
			return null;
		}
	}
}
