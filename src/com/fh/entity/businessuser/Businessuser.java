package com.fh.entity.businessuser;
public class Businessuser {
	private String BUSINESSUSER_ID;		//主键
	private String BUSINESSUSERNAME;	//商家名称
	private String BUSINESSPWD;			//登录密码
	private String TEL;					//手机
	private String COMPANYADDRESS;		//公司地址
	private String COMPANYNAME;			//公司名称
	private String BUSINESSLICENSEURL;	//营业执照  1正常2退出  默认：1
	private String SHOPNAME;			//店铺名称
	private String SHOPADDRESS;			//店铺地址
	private String SHOPLOGO;			//店铺logo
	private String WORK;				//主营业务
	private String FLAG;				//1正常2禁用3下架
	private String CREATIME;			//创建时间
	private String CONTACTSNAME;		//联系人
	private String SQFLAG;				//1未处理2已处理
	private String ZCFLAG;				//1待审核2审核通过3审核失败4填写审核
	private String CLJG;				//处理结果
	private int VISITNUM;				//浏览数量
	private String TYPE;				//1申请商家2注册商家3后台添加商家
	private String FBUSINESSUSER_ID;	//父帐号id
	private String FFLAG;				//1正常2禁用
	
	//**********************二期修改******************************
	private String legal;				//法人
	private String idCard;				//身份证
	private String BUSINESSLICENSEPath;	//营业执照路径
	
	public int getVISITNUM() {
		return VISITNUM;
	}
	public void setVISITNUM(int vISITNUM) {
		VISITNUM = vISITNUM;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getFBUSINESSUSER_ID() {
		return FBUSINESSUSER_ID;
	}
	public void setFBUSINESSUSER_ID(String fBUSINESSUSER_ID) {
		FBUSINESSUSER_ID = fBUSINESSUSER_ID;
	}
	public String getFFLAG() {
		return FFLAG;
	}
	public void setFFLAG(String fFLAG) {
		FFLAG = fFLAG;
	}
	public String getBUSINESSUSER_ID() {
		return BUSINESSUSER_ID;
	}
	public void setBUSINESSUSER_ID(String bUSINESSUSER_ID) {
		BUSINESSUSER_ID = bUSINESSUSER_ID;
	}
	public String getBUSINESSUSERNAME() {
		return BUSINESSUSERNAME;
	}
	public void setBUSINESSUSERNAME(String bUSINESSUSERNAME) {
		BUSINESSUSERNAME = bUSINESSUSERNAME;
	}
	public String getBUSINESSPWD() {
		return BUSINESSPWD;
	}
	public void setBUSINESSPWD(String bUSINESSPWD) {
		BUSINESSPWD = bUSINESSPWD;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getCOMPANYADDRESS() {
		return COMPANYADDRESS;
	}
	public void setCOMPANYADDRESS(String cOMPANYADDRESS) {
		COMPANYADDRESS = cOMPANYADDRESS;
	}
	public String getCOMPANYNAME() {
		return COMPANYNAME;
	}
	public void setCOMPANYNAME(String cOMPANYNAME) {
		COMPANYNAME = cOMPANYNAME;
	}
	public String getBUSINESSLICENSEURL() {
		return BUSINESSLICENSEURL;
	}
	public void setBUSINESSLICENSEURL(String bUSINESSLICENSEURL) {
		BUSINESSLICENSEURL = bUSINESSLICENSEURL;
	}
	public String getSHOPNAME() {
		return SHOPNAME;
	}
	public void setSHOPNAME(String sHOPNAME) {
		SHOPNAME = sHOPNAME;
	}
	public String getSHOPADDRESS() {
		return SHOPADDRESS;
	}
	public void setSHOPADDRESS(String sHOPADDRESS) {
		SHOPADDRESS = sHOPADDRESS;
	}
	public String getSHOPLOGO() {
		return SHOPLOGO;
	}
	public void setSHOPLOGO(String sHOPLOGO) {
		SHOPLOGO = sHOPLOGO;
	}
	public String getWORK() {
		return WORK;
	}
	public void setWORK(String wORK) {
		WORK = wORK;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getCREATIME() {
		return CREATIME;
	}
	public void setCREATIME(String cREATIME) {
		CREATIME = cREATIME;
	}
	public String getCONTACTSNAME() {
		return CONTACTSNAME;
	}
	public void setCONTACTSNAME(String cONTACTSNAME) {
		CONTACTSNAME = cONTACTSNAME;
	}
	public String getSQFLAG() {
		return SQFLAG;
	}
	public void setSQFLAG(String sQFLAG) {
		SQFLAG = sQFLAG;
	}
	public String getZCFLAG() {
		return ZCFLAG;
	}
	public void setZCFLAG(String zCFLAG) {
		ZCFLAG = zCFLAG;
	}
	public String getCLJG() {
		return CLJG;
	}
	public void setCLJG(String cLJG) {
		CLJG = cLJG;
	}
	public String getLegal()
	{
		return legal;
	}
	public void setLegal(String legal)
	{
		this.legal = legal;
	}
	public String getIdCard()
	{
		return idCard;
	}
	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}
	public String getBUSINESSLICENSEPath()
	{
		return BUSINESSLICENSEPath;
	}
	public void setBUSINESSLICENSEPath(String bUSINESSLICENSEPath)
	{
		BUSINESSLICENSEPath = bUSINESSLICENSEPath;
	}

	

}
