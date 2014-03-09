package cn.cdu.edu.TQC.bems.Algorithm;

public class  Base
{
	public Base(){
	
	}
	/*
	*Desc:get the max value for a  nonmultidimensional array
	*求最大值
	*/
	public static double max( double[] sample){
		int n = sample.length;
		double result =sample[0];
		for (int i=1;i<n ;i++ )
		{
			if (sample[i]>result)
			{
				result=sample[i];
			}
		}
		return result;	
	}

	/*
	*Desc:get the min value for a nonmultidimensional array
	*求最小值
	**/
	public static double min( double[] sample){
		int n = sample.length;
		double result =sample[0];
		for (int i=1;i<n ;i++ )
		{
			if (sample[i]<result)
			{
				result=sample[i];
			}
		}
		return result;	
	}

	/*
	*Desc:get the average value  for a nonmultidimensional array
	*求平均值
	*/
	public static double avg( double[] sample){
		int n = sample.length;
		double result =sample[0];
		for (int i=1;i<n ;i++ )
		{
			result +=sample[i];
		}
		return result/n;	
	}

	/*
	*Desc: get the variance (方差)for a nonmultidimensional array
	* （Inpute）输入参数：
	*     @param double[] sample 样本数组
	* （OutPute）输出参数：
	*     @return double if ＝＝－1，样本为空
	* （Logic）计算逻辑：
	*     sample － 平均值的平方求和
	*     
	*     
	*     方差
	**/
	public static double val( double[] sample){
		int n = sample.length;
		double result = -1;
		if (n<=0)
		{
			return result ;
		}else{
			double avg = avg(sample);
			result = 0;
			for(int j=0;j<n;j++)
				result += (sample[j]-avg)*(sample[j]-avg);
		
		}
		 result/=n;
		 return result;	
	}

	/*
	*Desc: get the standard variance(标准方差) for a nonmultidimensional array
	* (Inpute)输入参数：
	*     @param double[] sample 样本数组
	* (Output)输出参数：
	*     @return double if ＝＝－1，样本为空
	* (Logic)计算逻辑：
	*     sample[]的方差开方
	*     
	*     
	*     标准方差
	**/
	public static double stdval (double[] sample){
	    int n = sample.length;
		double result = -1;
		if(n<=0){
			return result;
		}else {
			result = val(sample);
			result = Math.sqrt(result);		
		}
		return result;	
	}


	/*
	*Desc: get the max distance  for a nonmultidimensional array
	* (Inpute)输入参数：
	*     @param double[] sample 样本数组
	* (Outpute)输出参数：
	*     @return double if ＝＝－1，样本为空
	* （Logic）计算逻辑：
	*     The array of sample 's max value subtract Its
	*     min value [sample[]的最大值-最小值]
	*     
	*     一位数组的最大差距
	**/
	public static double maxdis (double[] sample){
	    int n = sample.length;
		double result = -1;
		if(n<=0){
			return result;
		}else {
			result = max(sample)-min(sample);		
		}
		return result;	
	}

	/*
	*Desc： get the average warp for a nonmultidimensional array
	* (Inpute)输入参数：
	*     @param double[] sample 样本数组
	* (Outpute)输出参数：
	*     @return double if ＝＝－1，样本为空
	* (Logic)计算逻辑：
	*     sample[]的每一个元素-平均值的绝对值的平均值
	**/
	public static double  meddev (double[] sample){
	    int n = sample.length;
		double result = -1;
		if(n<=0){
			return result;
		}else {
			double avg = avg(sample);
			result =0;
			for(int j=0;j<n;j++){
				result += Math.abs(sample[j]-avg);
			}
		}
		result /=n;
		return result;	
	}

	/*
	*Desc: get the inside multiplication of  two array
	*(Inpute)输入参数：
	*     @param double[] sample1
	*     @param double[] sample2
	*(OutPute)输出参数：
	*     @return double res;
	*(Logic)处理逻辑：
	*   Sum the value of two array's corresponding element production [对应元素乘积的和]
	**/

	public static double inmul(double[] sample1,double[] sample2){
	       double result = 1.0/0.0;
		   int len1 = sample1.length;
		   int len2 = sample2.length;
		   if(len1 != len2){
				System.out.println("two vector dose not at the same dimension,please check it\n");
				return result ;		   
		   }else{
				result = 0;
				for (int i=0;i<len1 ;i++ )
				{
					result += sample1[i]*sample2[i];
				}
		  		   
		   }
		   return result;	
	}

	/*
	*Desc : get the opposite production of two array to comprise a new array
	*(Inpute)输入参数：
	*     @param double[] sample1
	*     @param double[] sample2
	*(Outpute)输出参数：
	*     @return double[] result;
	*(Logic)处理逻辑：
	*   形成新的向量，元素为对应元素的乘积
	**/

	public static double[] vecmul(double[] sample1,double[] sample2){
		   int len1 = sample1.length;
		   int len2 = sample2.length;
		   double[] result = new double[len1];
		   if(len1 != len2){
				System.out.println("two vector dose not at the same dimension,please check it\n");
				return result ;		   
		   }else{
				for (int i=0;i<len1 ;i++ )
				{
					result[i]= sample1[i]*sample2[i];
				}
		  		   
		   }
		   return result;	
	}


	/*
	*Desc : sort the element of the array into a from  great to little order
	*/
	public  void  decorder(double[] sample){
		int n = sample.length;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if (sample[i]<sample[j])
				{
					double temp = sample[i];
					sample[i]=sample[j];
					sample[j]=sample[i];
				}		
			}
		}
	}

	/*
	*Desc : sort the element of the array into a from  little to great order
	*/
	public  void  ascorder(double[] sample){
		int n = sample.length;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if (sample[i]>sample[j])
				{
					double temp = sample[i];
					sample[i]=sample[j];
					sample[j]=sample[i];
				}		
			}
		}
	}

   /*
   *Desc: get two point distance of standard (Euclid distance)
   *Inpute:
   *     @param  double[] Pfrom
   *     @param  double[] Pto
   * Outpute:
   *     @param  double res
   * Logic:
   */
   public static double eudis(double[] Pfrom ,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double res = 0;
		for(int i=0;i<Lfrom;i++){
			double temp = Pfrom[i]-Pto[i];
			temp*=temp;
			res+=temp;	
		}
		return Math.sqrt(res);   
   }

   /*
   *Desc : get the min value of two array's corresponding element's distance
   *Inpute:
   *	@param  double[] Pfrom
   *	@parma  double[] Pto
   *Outpute:
   *	@return double res
   *Logic:
   */
   public static double mindis(double[] Pfrom,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double res = Math.abs(Pfrom[0]-Pto[0]);
		for(int i=1;i<Lfrom;i++){
			double temp = Math.abs(Pfrom[i]-Pto[i]);
			if (temp < res )
			{
				res =  temp;
			}
		}
		return res;   	   
   }

   /*
    *Desc : get the max value of two array's corresponding element's distance
    *Inpute:
    *	@param  double[] Pfrom
    *	@parma  double[] Pto
    *Outpute:
    *	@return double res
    *Logic:
    */
   public static double maxdis(double[] Pfrom,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double res = Math.abs(Pfrom[0]-Pto[0]);
		for(int i=1;i<Lfrom;i++){
			double temp = Math.abs(Pfrom[i]-Pto[i]);
			if (temp > res )
			{
				res =  temp;
			}
		}
		return res;   	   
   }


   /*
    *Desc : get the total sum value of two array's corresponding element's distance
    *Inpute:
    *	@param  double[] Pfrom
    *	@parma  double[] Pto
    *Outpute:
    *	@return double res
    *Logic:
    */
   public static double sumdis(double[] Pfrom,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double res = Math.abs(Pfrom[0]-Pto[0]);
		for(int i=1;i<Lfrom;i++){
			double temp = Math.abs(Pfrom[i]-Pto[i]);
			res += temp;
		}
		return res;   	   
   }

   /*
    *Desc : get the averagedistance of two array's corresponding element's distance
    *Inpute:
    *	@param  double[] Pfrom
    *	@parma  double[] Pto
    *Outpute:
    *	@return double res
    *Logic:
    */
   public static double dimaddavg(double[] Pfrom,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double res = Math.abs(Pfrom[0]-Pto[0]);
		for(int i=1;i<Lfrom;i++){
			double temp = Math.abs(Pfrom[i]-Pto[i]);
			res += temp;
		}
		return res/Lfrom;   	   
   }

   /*
    *Desc : get the diemnsional id  of two array's corresponding element's distance 
    *       who has tha max value 
    *Inpute:
    *	@param  double[] Pfrom
    *	@parma  double[] Pto
    *Outpute:
    *	@return Int  id
    *Logic:
    */
   public static int maxerrordim(double[] Pfrom,double[] Pto){
		int Lfrom = Pfrom.length;
		int Lto = Pto.length;
		if (Lfrom != Lto)
		{
			return -1;
		}
		double maxerror=Math.abs(Pfrom[0]-Pto[0]);
		int res = 0;
		for(int i=1;i<Lfrom;i++){
			double temp = Math.abs(Pfrom[i]-Pto[i]);
			if ( temp>maxerror)
			{
				res = i;
				maxerror=temp;
			}
		}
		return res;   
   }

}
