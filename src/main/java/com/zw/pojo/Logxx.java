package com.zw.pojo;

public class Logxx {

	/*log.setDescription(getServiceMthodDescription(joinPoint));   
    log.setExceptionCode(e.getClass().getName());  
    log.setType("1");  //“Ï≥£¿‡–Õ
    log.setExceptionDetail(e.getMessage());  
    log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
    log.setParams(params);  
    log.setCreateBy(user);  
    log.setCreateDate(DateUtil.getCurrentDate());  
    log.setRequestIp(ip);*/
    
	private String description;
	private String exceptionCode;
	private Integer type;
	private String exceptionDetail;
	private String method;
	private String params;
	private String createBy;
	private String createDate;
	private String requestIp;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	
}
