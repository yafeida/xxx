package com.monkey.wx.service;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.binary.Hex;
import com.alibaba.fastjson.JSONObject;
import com.monkey.common.util.DateUtil;
import com.monkey.common.util.HttpUtils;
import com.monkey.common.util.MD5Utils;
import com.monkey.common.util.RSAEncrypt;

import springfox.documentation.spring.web.json.Json;
@Service
public class ChargeService extends BaseService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 提交充值
	 * @param req					
	 * @return	返回json格式的充值信息
	 */
	public JSONObject submitRecharge(HttpServletRequest req) {
		Map<String,Object> param = getMaps(req);
		String phoneNo = param.get("phone")+"";				//手机号
		Double fee = Double.parseDouble(param.get("amount")+"")*100;	
		int spec = fee.intValue();							//充值金额 ,以分为单位
//		String discount = param.get("discount")+"";			//折扣后金额
		String terminalName = "yangdekui";					//终端接口商户的登陆名
		String timeStamp = DateUtil.getDateFormat(new Date(), "yyyyMMddHHmmssSSS");		//时间戳，格式：yyyyMMddHHmmssSSS
		String customerOrderId = timeStamp+phoneNo+getFourSquece();						//客户32位订单号
		String orderType = "2";								//业务操作类型(固定参数值：2)
		String scope = "nation";							//使用范围(固定参数填写：nation)
		String callbackUrl = "http://www.baidu.com";		//回调地址
		String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMSeB7dxyodP/lF/MKjUMOr+BzjygEUDmYYsL4ZxFBrfSRNOgoaBexLSNEOa1F8qvDRySvz2ngBBOSEFeWpiB+VDcH5/m6VWOvBc/ifktIsiNNioH+Y3nuvAhZDJPb63pnsQoafAVQisFigHH29S9RgUMBmnisCOnfqAeaALDJXJAgMBAAECgYB1fuqlmX6qNgeItDVj7sn85Cl0wpYcp/YK1ETiVpjpJh4JR5WAw8fEi5FoBRJ8fJFT1ikx2j65cECvlxHjnQmRz/dXqqksX2NGzlKgl6rjYdsJ1c3B51ur4kLpHongORbZ0ycqVax9HshxwmwPwzSl9NO2I1uAeFkZzGEncFPwAQJBAORwNCPW/0pNG7MAdn2Rk4MXMdicl5VL9KvWDsrFRhHKJ4I5adWLERJwus0sZ/Ssjt81TAHiPRvZwlIiPmPVzUkCQQDcVvfn1EpfmZk/sj3LW/+42rFKMdzjwiUWADMnKTq9ZgVQHq6hCZbjoWWFQ/ATmzrIuPJ3p+nJWnFn7DVTQwSBAkA4nR4u66+lSGre9/Rltlhqmqr91gEzPPnMRGayFiYBbqpP+c6i365BdbK1+pVg7AsUgm7t4JXoexVPqbDwqSIRAkA+4lM+RsgzIJMsKqKYN1l0WHXgxvpTxkTvUn9G3e11wHgjtZ4BN8toi9d9gTYzYkoUnzQUEkgWv0k+b5uId8gBAkBc8XBMhgH/k9dLGNKFtFkWaahva2dufkIdoszut20lfw+V4rNLWOHvjSKqv53REK96H1qNdXKBc7a5uv6mW39J";
		String url ="http://118.31.229.23:8080/bosspaybill/order/quotaOrder";			//请求地址
		//对参数进行拼接
		String paramStr = "callbackUrl="+callbackUrl+"&customerOrderId="+customerOrderId+"&orderType="+orderType+"&phoneNo="+phoneNo
							+"&scope="+scope+"&spec="+spec+"&terminalName="+terminalName+"&timeStamp="+timeStamp;
		String signature = generateSignature(paramStr,privateKey);						//数据私钥签名	
		paramStr = paramStr+"&signature="+signature;									//get请求参数
		JSONObject json = null;
		//HTTP GET请求
		try {
			json = JSONObject.parseObject(HttpUtils.sendGet(url,paramStr));
		} catch (IOException e) {
			logger.error("话费充值异常");
			e.printStackTrace();
		}
		return json;	
	}
}
