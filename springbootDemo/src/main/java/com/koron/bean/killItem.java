package com.koron.bean;

/*
 * 秒杀明细实体表
 */
public class killItem {
	//管理列表
//    @NamedStoredProcedureQuery(name = "getProcedurekill", procedureName = "getProcedurekill",
//            parameters = {
//                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "a_theme_code", type = String.class),
//                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "num", type = Integer.class)  // 记录满足条件的总条数
//             }),
    //记录id
	private Integer id;
	//产品id
	private Integer proid;
	//用户手机号码
	private String mobile;
	//下单数量
	private Integer downum;
	
	//返回结果
	private Integer r_result;
	
	public Integer getR_result() {
		return r_result;
	}
	public void setR_result(Integer r_result) {
		this.r_result = r_result;
	}
	public Integer getId() {
		return id;
	}
	public Integer getProid() {
		return proid;
	}
	public String getMobile() {
		return mobile;
	}
	
	public Integer getDownum() {
		return downum;
	}
	public void setDownum(Integer downum) {
		this.downum = downum;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
