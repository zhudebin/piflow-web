
package com.nature.component.flow.vo;

import java.io.Serializable;

/**
 * stop的属性
 * 
 * @author Nature
 *
 */

public class StopsPropertyVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private StopsVo stopsVo;

	private String name;

	private String displayName;

	private String description;

	private String customValue;

	private String allowableValues;

	private Boolean required;

	private Boolean sensitive;
	
	private Boolean isSelect;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StopsVo getStopsVo() {
		return stopsVo;
	}

	public void setStopsVo(StopsVo stopsVo) {
		this.stopsVo = stopsVo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomValue() {
		return customValue;
	}

	public void setCustomValue(String customValue) {
		this.customValue = customValue;
	}

	public String getAllowableValues() {
		return allowableValues;
	}

	public void setAllowableValues(String allowableValues) {
		this.allowableValues = allowableValues;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getSensitive() {
		return sensitive;
	}

	public void setSensitive(Boolean sensitive) {
		this.sensitive = sensitive;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}
}
