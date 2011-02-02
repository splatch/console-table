/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.code_house.console.table;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.code_house.console.table.HAlign.*;

/**
 * Test of HAlign type.
 * 
 * @author ldywicki
 */
public class HAlignTest {

    /**
     * Test value.
     */
    final static String TEXT = "TEXT";

    @Test
    public void centerOdd() {
        // given
        String position = center.position(TEXT, 5);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(1, position);
    }

    @Test
    public void centerNoSpaces() {
        // given
        String position = center.position(TEXT, 4);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(0, position);
    }

    @Test
    public void centerEven() {
        // given
        String position = center.position(TEXT, 8);

        // then
        assertNumberOfSpacesFromLeft(2, position);
        assertNumberOfSpacesFromRight(2, position);
    }

    @Test
    public void rightOdd() {
        // given
        String position = right.position(TEXT, 5);

        // then
        assertNumberOfSpacesFromLeft(1, position);
        assertNumberOfSpacesFromRight(0, position);
    }

    @Test
    public void rightNoSpaces() {
        // given
        String position = center.position(TEXT, 4);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(0, position);
    }

    @Test
    public void rightEven() {
        // given
        String position = right.position(TEXT, 8);

        // then
        assertNumberOfSpacesFromLeft(4, position);
        assertNumberOfSpacesFromRight(0, position);
    }

    @Test
    public void leftOdd() {
        // given
        String position = left.position(TEXT, 5);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(1, position);
    }

    @Test
    public void leftNoSpaces() {
        // given
        String position = left.position(TEXT, 4);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(0, position);
    }

    @Test
    public void leftEven() {
        // given
        String position = left.position(TEXT, 8);

        // then
        assertNumberOfSpacesFromLeft(0, position);
        assertNumberOfSpacesFromRight(4, position);
    }

    /**
     * Utility method which checks number of spaces at the text begining.
     * 
     * @param number Number of white spaces.
     * @param text Text to check.
     */
    public void assertNumberOfSpacesFromLeft(int number, String text) {
        assertEquals("Text does not contain " + number + " space(s) at the begining.",
            "", text.substring(0, number).trim());
    }

    public void assertNumberOfSpacesFromRight(int number, String text) {
        assertEquals("Text does not contain " + number + " space(s) at the end.",
            "", text.substring(text.length() - number).trim());
    }
}
