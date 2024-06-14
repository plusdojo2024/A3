package logic;
/*
import java.util.List;

import model.UserIp;

//その時ログインを試みているユーザーがリストにいるかチェックして存在すればTrueを返す
public class IpCheckLogic {
	public boolean listCheck(UserIp ip, List<UserIp> ipList) {
		boolean result = false;

		for (UserIp list : ipList) {
			if (list.getIpAddress().equals(ip.getIpAddress())) {
				result = true;
			}
		}
		return result;
	}

	//その時ログインを試みているユーザーをリストから取り出して返す
	public UserIp searchUser(UserIp ip, List<UserIp> ipList) {

		for(int i=0;i<ipList.size();i++) {
			if (ipList.get(i).getIpAddress().equals(ip.getIpAddress())) {//ipアドレスが一致
				ip.setCount(ipList.get(i).getCount());
				ip.setDate(ipList.get(i).getDate());
			}
		}
		return ip;
	}

	public void removeList(UserIp ip, List<UserIp> ipList) {
		for(int i=0;i<ipList.size();i++) {
			if (ipList.get(i).getIpAddress().equals(ip.getIpAddress())) {//ipアドレスが一致
				ipList.remove(i);//リストから削除
			}
		}
	}

	public void updateList(UserIp ip, List<UserIp> ipList) {
		for(int i=0;i<ipList.size();i++) {
			if (ipList.get(i).getIpAddress().equals(ip.getIpAddress())) {//ipアドレスが一致
				ipList.set(i,ip);//リストを更新
			}
		}
	}
	public int userGetCount(UserIp ip, List<UserIp> ipList) {
		int count=0;
		for(int i=0;i<ipList.size();i++) {
			if (ipList.get(i).getIpAddress().equals(ip.getIpAddress())) {//ipアドレスが一致
				count = ipList.get(i).getCount();
			}
		}
		return count;
	}
	public int userCountPlus(UserIp ip, List<UserIp> ipList) {
		int count=0;
		for(int i=0;i<ipList.size();i++) {
			if (ipList.get(i).getIpAddress().equals(ip.getIpAddress())) {//ipアドレスが一致
				count = ipList.get(i).getCount()+1;
			}
		}
		return count;
	}
}
*/
