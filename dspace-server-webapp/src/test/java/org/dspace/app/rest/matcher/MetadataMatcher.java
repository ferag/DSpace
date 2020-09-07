/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.matcher;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * Utility class to provide convenient matchers for metadata.
 */
public class MetadataMatcher {

    private MetadataMatcher() {
    }

    /**
     * Gets a matcher to ensure a given value is present among all values for a given metadata key.
     *
     * @param key the metadata key.
     * @param value the value that must be present.
     * @return the matcher.
     */
    public static Matcher<? super Object> matchMetadata(String key, String value) {
        return hasJsonPath("$.['" + key + "'][*].value", hasItem(value));
    }

    /**
     * Gets a matcher to ensure a given value is present at a specific position in the list of values for a given key.
     *
     * @param key the metadata key.
     * @param value the value that must be present.
     * @param position the position it must be present at.
     * @return the matcher.
     */
    public static Matcher<? super Object> matchMetadata(String key, String value, int position) {
        return hasJsonPath("$.['" + key + "'][" + position + "].value", is(value));
    }

    /**
     * Gets a matcher to ensure a given value is present at a specific position in
     * the list of values for a given key.
     *
     * @param key       the metadata key.
     * @param value     the value that must be present.
     * @param authority the authority that must be present.
     * @param position  the position it must be present at.
     * @return the matcher.
     */
    public static Matcher<? super Object> matchMetadata(String key, String value, String authority, int position) {
        Matcher<Object> hasValue = hasJsonPath("$.['" + key + "'][" + position + "].value", is(value));
        Matcher<Object> hasAuthority = hasJsonPath("$.['" + key + "'][" + position + "].authority", is(authority));
        return Matchers.allOf(hasValue, hasAuthority);
    }

    /**
     * Gets a matcher to ensure a given key is not present.
     *
     * @param key the metadata key.
     * @return the matcher.
     */
    public static Matcher<? super Object> matchMetadataDoesNotExist(String key) {
        return hasNoJsonPath("$.['" + key + "']");
    }
}