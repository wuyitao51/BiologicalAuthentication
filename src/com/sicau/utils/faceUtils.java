package com.sicau.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import java.lang.String;
import java.util.HashMap;

/*** ֱ��ʹ��JAR���������£�

 * 1.�ڹٷ���վ����Java SDKѹ�����߰���https://ai.baidu.com/download?sdkId=6����

 * 2.�����ص�aip-java-sdk-version.zip��ѹ�󣬸��Ƶ������ļ����С�

 * 3.��Eclipse�Ҽ������� -> Properties -> Java Build Path -> Add JARs����

 * 4.���SDK���߰�aip-java-sdk-version.jar�͵������������߰�json-20160810.jar log4j-1.2.17.jar��

 * ���У�versionΪ�汾�ţ������ɺ��û��Ϳ����ڹ�����ʹ��Face Java SDK��
 */

public class faceUtils {
    //����APPID/AK/SK
    public static final String APP_ID = "10111863";
    public static final String API_KEY = "NnldPNtt1FKGvV1PftN5iVAm";
    public static final String SECRET_KEY = "5VtUUUPpzj6aK5xqed1ggw9diuHrLoL1";
    public static final String GROUP_ID = "demo_1";

    public static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);  // ��ʼ��

    public JSONObject face_rec(String img_path) {  // ����1:N�ȶ�, ��Ҫ�ṩ����ͼƬ·��, ���ص�json����res�е�result�����е�uid�ֶ�Ϊƥ�䵽���û�ID
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();

        String groupId = GROUP_ID;

        String image = img_path;
        JSONObject res = client.multiIdentify(groupId, image, options);
        return res;
    }

    public JSONObject add_user(String id, String img_path) {  // �����û����贫�����û���ID�����û�����ͼƬ��·�����������ݰ���Ψһ�ֶ�log_id�������
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        String uid = id;
        String userInfo = "";
        String groupId = GROUP_ID;

        // ����Ϊ����ͼƬ·��
        String image = img_path;
        JSONObject res = client.addUser(uid, userInfo, groupId, image, options);
        return res;
    }

    public JSONObject update_user(String id, String img_path) {  // # �����û����贫���û���ID���û��µ�����ͼƬ��·�����������ݰ���Ψһ�ֶ�log_id�������
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        String uid = id;
        String userInfo = "";
        String groupId = GROUP_ID;

        // ����Ϊ����ͼƬ·��
        String image = img_path;
        JSONObject res = client.updateUser(uid, userInfo, groupId, image, options);
        return res;
    }

    public JSONObject delete_user(String id) {  // # ɾ���û�����Ҫ�����ɾ���û���ID
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();

        String uid = id;

        // ����ɾ��
        JSONObject res = client.deleteUser(uid, options);
        return res;
    }
}
