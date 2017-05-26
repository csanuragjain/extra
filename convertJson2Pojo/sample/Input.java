
package com.cooltrickshome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "sport",
    "age",
    "id",
    "lastScores"
})
public class Input {

    @JsonProperty("name")
    private String name;
    @JsonProperty("sport")
    private String sport;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lastScores")
    private List<Integer> lastScores = new ArrayList<Integer>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Input withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("sport")
    public String getSport() {
        return sport;
    }

    @JsonProperty("sport")
    public void setSport(String sport) {
        this.sport = sport;
    }

    public Input withSport(String sport) {
        this.sport = sport;
        return this;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    public Input withAge(Integer age) {
        this.age = age;
        return this;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Input withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("lastScores")
    public List<Integer> getLastScores() {
        return lastScores;
    }

    @JsonProperty("lastScores")
    public void setLastScores(List<Integer> lastScores) {
        this.lastScores = lastScores;
    }

    public Input withLastScores(List<Integer> lastScores) {
        this.lastScores = lastScores;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Input withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(sport).append(age).append(id).append(lastScores).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Input) == false) {
            return false;
        }
        Input rhs = ((Input) other);
        return new EqualsBuilder().append(name, rhs.name).append(sport, rhs.sport).append(age, rhs.age).append(id, rhs.id).append(lastScores, rhs.lastScores).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
