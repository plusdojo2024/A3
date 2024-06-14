package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/*randHash(String)で個別にランダムなソルトを割り振ったハッシュ化
 * ハッシュ値とソルトをオブジェクトのsetterで設定する
 * 後でゲッターで取得できる
*/
//getHash(String)で固定のソルトをかけてハッシュ化してハッシュ値を返す

//getIntHash(int)でint型(IDなど)をハッシュ化してハッシュ値を返す

public class HashLogic {
	private String salt;
	private String pw;

	//ハッシュ化したい文字列を受け取ってランダム生成のソルト値でハッシュ化
	//ハッシュ化したパスワードとソルト値をこのクラスオブジェクトのpwとsaltにセットする
	public void randHash(String password) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			StringBuilder stringBuilder = new StringBuilder();
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

			for (int i = 0; i < 16; i++) {
				int index = secureRandom.nextInt(characters.length());
				stringBuilder.append(characters.charAt(index));
			}

			String strSalt = stringBuilder.toString();
			this.setSalt(strSalt);

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hash = password + strSalt;

			md.update(hash.getBytes());

			byte[] hashBytes = md.digest();
			this.pw = Base64.getEncoder().encodeToString(hashBytes);
			this.setPw(pw);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getHash(String password) {
		String hash = "";

		try {
			String strSalt = "Ka0Ge69f2HmogE";

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			hash = password + strSalt;
			md.update(hash.getBytes());

			byte[] hashBytes = md.digest();
			hash = Base64.getEncoder().encodeToString(hashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

	//ユーザーが入力したパスワードにDBから取り出したソルトをかけてハッシュ化
	public boolean checkHash(String password, String dbPass, String salt) {
		String hash = "";
		boolean result = false;

		try {
			hash = password + salt;

			MessageDigest md = MessageDigest.getInstance("SHA-256");//SHA256を定義
			md.update(hash.getBytes());//hashをバイトデータに変換

			byte[] hashBytes = md.digest();//byteはString型のように並べられないので配列にする
			hash = Base64.getEncoder().encodeToString(hashBytes);//ハッシュ化処理してStringに変換してhashに代入

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//dbから取り出したソルトを元にハッシュ化したパスワードとdb内のパスワードを比較して一致ならTrueにする。
		if (hash.equals(dbPass)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public String getIntHash(int id) {
		String hash = "";
		Integer intPass = Integer.valueOf(id);
		String password = intPass.toString();

		try {
			String strSalt = "Ka0Ge69f2HmogE";

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(strSalt.getBytes());
			md.update(password.getBytes());

			byte[] hashBytes = md.digest();
			hash = Base64.getEncoder().encodeToString(hashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

	//ハッシュ化したい文字列とソルト値を受け取ってハッシュ化して返す
	public String createRandHash(String pass, String salt) {
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			pass = pass + salt;
			md.update(pass.getBytes());

			byte[] hashBytes = md.digest();
			pass = Base64.getEncoder().encodeToString(hashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return pass;
	}

	//ハッシュ化したい数値とソルト値を受け取ってハッシュ化して返す
	public String createRandHashInt(int pass, String salt) {
		String strPass = "";
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			strPass = Integer.toString(pass);

			strPass = strPass + salt;
			md.update(strPass.getBytes());

			byte[] hashBytes = md.digest();
			strPass = Base64.getEncoder().encodeToString(hashBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return strPass;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getSalt() {
		return salt;

	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
