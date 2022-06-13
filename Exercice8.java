import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

// Solution
public class Exercice8 {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static BigInteger[][] C;
	
	static void solve()
	{
		C = new BigInteger[130 + 1][130 + 1];
		for (int i = 0; i <= 130; i++) {
			Arrays.fill(C[i], BigInteger.ZERO);
			C[i][0] = BigInteger.ONE;
			for (int j = 1; j <= i; j++) {
				C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
			}
		}

		for(int T = ni(), cas = 1;T > 0;T--,cas++){
			out.print("Case #" + cas + ": ");
			go();
		}
	}

	static BigInteger countValid(int[] fs, int[] ss)
	{
		int n = ss.length;
		if(n == 3){
			BigInteger num = BigInteger.ZERO;
			for(int i = 0;i <= fs[1];i++){
				for(int j = 0;j <= fs[2];j++){
					for(int k = 0;k <= fs[3];k++){
						int t0 = ss[0] - (i + fs[2] - j + k);
						int t1 = ss[1] - (fs[1]-i + j + k);
						int t2 = ss[2] - (fs[1]-i + fs[2] - j + fs[3] - k);
						if(t0 == t1 && t1 == t2 && t0 >= 0 && t0 <= fs[0]){
							num = num.add(C[fs[0]][t0].multiply(C[fs[1]][i]).multiply(C[fs[2]][j]).multiply(C[fs[3]][k]));
						}
					}
				}
			}
			return num;
		}else if(n == 2){
			BigInteger num = BigInteger.ZERO;
			for(int i = 0;i <= fs[1];i++){
				int t0 = ss[0] - i;
				int t1 = ss[1] - (fs[1]-i);
				if(t0 == t1 && t0 >= 0 && t0 <= fs[0]){
					num = num.add(C[fs[0]][t0].multiply(C[fs[1]][i]));
				}
			}
			return num;
		}else{
			throw new RuntimeException();
		}
	}

	static void go()
	{
		int n = ni(), Q = ni();
		char[][] qs = new char[n][];
		int[] ss = new int[n];
		for(int i = 0;i < n;i++){
			qs[i] = ns(Q);
			ss[i] = ni();
		}
		if(n == 1){
			if(ss[0]*2 >= Q){
				out.println(new String(qs[0]) + " " + ss[0] + "/" + 1);
			}else{
				for(int j = 0;j < Q;j++){
					out.print(qs[0][j] == 'T' ? 'F' : 'T');
				}
				out.println(" " + (Q-ss[0]) + "/" + 1);
			}
			return;
		}

		final int mask = (1<<n)-1;
		int[] fs = new int[1<<n-1];
		for(int i = 0;i < Q;i++){
			int u = 0;
			for(int j = 0;j < n;j++){
				if(qs[j][i] == 'T'){
					u |= 1<<j;
				}
			}
			if((mask^u) < u){
				fs[mask^u]++;
			}else{
				fs[u]++;
			}
		}
		BigInteger base = countValid(fs, ss);
		BigInteger[][] hs = new BigInteger[1<<n-1][2];
		for(int i = 0;i < 1<<n-1;i++){
			for(int j = 0;j < 2;j++){
				int x = i;
				if(j == 1)x ^= (1<<n)-1;
				for(int k = 0;k < n;k++){
					if(x<<~k<0){
						ss[k]--;
					}
				}
				fs[i]--;
				hs[i][j] = countValid(fs, ss);
				fs[i]++;
				for(int k = 0;k < n;k++){
					if(x<<~k<0){
						ss[k]++;
					}
				}
			}
		}

		for(int i = 0;i < Q;i++){
			int u = 0;
			for(int j = 0;j < n;j++){
				if(qs[j][i] == 'T'){
					u |= 1<<j;
				}
			}
			int v = u;
			if((mask^u) < u){
				v = mask^u;
			}

			if(hs[v][0].compareTo(hs[v][1]) >= 0){
				if(u == v){
					out.print("T");
				}else{
					out.print("F");
				}
			}else{
				if(u == v){
					out.print("F");
				}else{
					out.print("T");
				}
			}
		}

		FB ans = new FB(0);
		for(int i = 0;i < 1<<n-1;i++){
			if(fs[i] == 0)continue;
			BigInteger num = hs[i][0].max(hs[i][1]);
			BigInteger den = hs[i][0].add(hs[i][1]);
			FB a = new FB(num, den);
			ans = FB.add(ans, FB.mul(a, new FB(fs[i])));
		}
		out.println(" " + ans);
	}

	public static class FB implements Comparable<FB> {
		public BigInteger num, den;

		public FB(){ this.num = BigInteger.ZERO; this.den = BigInteger.ONE; }
		public FB(long num) { this.num = BigInteger.valueOf(num); this.den = BigInteger.ONE; }
		public FB(long num, long den) { this.num = BigInteger.valueOf(num); this.den = BigInteger.valueOf(den); reduce(); }
		public FB(BigInteger num) {	this.num = num; this.den = BigInteger.ONE;}
		public FB(BigInteger num, BigInteger den) {	this.num = num;	this.den = den;	reduce();}
		public FB(BigDecimal d) {
			int scale = d.scale();
			num = d.scaleByPowerOfTen(scale).toBigInteger();
			den = BigInteger.valueOf(10).pow(scale);
			reduce();
		}

		public FB neg(){ return new FB(num.negate(), den);}
		public FB abs(){ return new FB(num.abs(), den);}

		public FB reduce()
		{
			if(den.signum() == 0) {
			}else if(num.signum() == 0) {
				den = BigInteger.ONE;
			}else {
				if(den.signum() < 0) {
					num = num.negate();
					den = den.negate();
				}
				BigInteger g = num.gcd(den);
				num = num.divide(g);
				den = den.divide(g);
			}
			return this;
		}

		public static FB add(FB a, FB b){ return new FB(a.num.multiply(b.den).add(a.den.multiply(b.num)), a.den.multiply(b.den)); }
		public static FB sub(FB a, FB b){ return new FB(a.num.multiply(b.den).subtract(a.den.multiply(b.num)), a.den.multiply(b.den)); }
		public static FB mul(FB a, FB b){ return new FB(a.num.multiply(b.num), a.den.multiply(b.den)); }
		public static FB div(FB a, FB b){ return new FB(a.num.multiply(b.den), a.den.multiply(b.num)); }
		public static FB inv(FB a) { return new FB(a.den, a.num); }

		public FB add(FB b){ num = num.multiply(b.den).add(den.multiply(b.num)); den = den.multiply(b.den); return reduce(); }
		public FB sub(FB b){ num = num.multiply(b.den).subtract(den.multiply(b.num)); den = den.multiply(b.den); return reduce(); }
		public FB mul(FB b){ num = num.multiply(b.num); den = den.multiply(b.den); return reduce(); }
		public FB div(FB b){ num = num.multiply(b.den); den = den.multiply(b.num); return reduce(); }
		public FB inv() { BigInteger d = num; num = den; den = d; return reduce(); }

		public String toString() { return num.toString() + "/" + den.toString(); }
		public String toStringSimple() { return den.equals(BigInteger.ONE) ? num.toString() : num.toString() + "/" + den.toString(); }

		public int compareTo(FB f) { return num.multiply(f.den).compareTo(f.num.multiply(den)); }

		public boolean simpleEquals(FB f) {	return num.equals(f.num) && den.equals(f.den); }

		public boolean equals(Object o)
		{
			if(o == null)return false;
			FB f = (FB) o;
			return num.multiply(f.den).equals(f.num.multiply(den));
		}

	}


	public static void main(String[] args) throws Exception
	{
//		int n = 3, m = 120;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(2021 + "\n");
//		for(int u = 0;u < 2021;u++) {
//			sb.append(n + " ");
//			sb.append(m + " ");
//			for(int j = 0;j < n;j++) {
//				for (int i = 0; i < m; i++) {
////					sb.append("FT".charAt(gen.nextInt(2)));
//					int x = i/15;
//					sb.append(x<<~j<0 ? "T" : "F");
//				}
//				sb.append(" " + 60 + "\n");
//			}
//		}
//		INPUT = sb.toString();


		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G-S+"ms");
	}
	
	private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
//	private static boolean isSpaceChar(int c) { return !(c >= 32 && c <= 126); }
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private static int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
