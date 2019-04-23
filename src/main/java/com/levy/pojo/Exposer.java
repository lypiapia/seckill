package com.levy.pojo;

/**
 * ��¶��ɱ��ַ
 * @author Levy
 *
 */
public class Exposer {
	//�Ƿ�����ɱ
	private boolean isExposed;
	
	//���ܴ�ʩ
	private String md5;
	
	//idΪseckillId����Ʒ
	private long seckillId;
	
	//ϵͳ��ǰʱ��(��λ��ms)
	private long now;
	
	//��ɱ����ʱ��
	private long start;
	
	//��ɱ����ʱ��
	private long end;

	public Exposer(boolean isExposed, String md5, long seckillId, long now, long start, long end) {
		super();
		this.isExposed = isExposed;
		this.md5 = md5;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	 public Exposer(boolean exposed, long seckillId, long now, long start, long end){ 
		 	this.isExposed = exposed; 
		 	this.seckillId = seckillId; 
		 	this.now = now; 
		 	this.start = start; 
		 	this.end = end; 
		}

	public Exposer(boolean isExposed, String md5, long seckillId) {
		super();
		this.isExposed = isExposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean isExposed, long seckillId) {
		super();
		this.isExposed = isExposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return isExposed;
	}

	public void setExposed(boolean isExposed) {
		this.isExposed = isExposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Exposer [isExposed=" + isExposed + ", md5=" + md5 + ", seckillId=" + seckillId + ", now=" + now
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
