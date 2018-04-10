package com.sicau.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import java.lang.String;
import java.util.HashMap;

/*** 直接使用JAR包步骤如下：

 * 1.在官方网站下载Java SDK压缩工具包（https://ai.baidu.com/download?sdkId=6）。

 * 2.将下载的aip-java-sdk-version.zip解压后，复制到工程文件夹中。

 * 3.在Eclipse右键“工程 -> Properties -> Java Build Path -> Add JARs”。

 * 4.添加SDK工具包aip-java-sdk-version.jar和第三方依赖工具包json-20160810.jar log4j-1.2.17.jar。

 * 其中，version为版本号，添加完成后，用户就可以在工程中使用Face Java SDK。
 */

public class faceUtils {
    //设置APPID/AK/SK
    public static final String APP_ID = "10111863";
    public static final String API_KEY = "NnldPNtt1FKGvV1PftN5iVAm";
    public static final String SECRET_KEY = "5VtUUUPpzj6aK5xqed1ggw9diuHrLoL1";
    public static final String GROUP_ID = "demo_1";

    public static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);  // 初始化

    public JSONObject face_rec(String img_path) {  // 人脸1:N比对, 需要提供本地图片路径, 返回的json数据res中的result参数中的uid字段为匹配到的用户ID
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String groupId = GROUP_ID;

        String image = img_path;
        JSONObject res = client.multiIdentify(groupId, image, options);
        return res;
    }

    public JSONObject add_user(String id, String img_path) {  // 增加用户，需传入新用户的ID，新用户人脸图片的路径，返回数据包含唯一字段log_id，随机数
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        String uid = id;
        String userInfo = "";
        String groupId = GROUP_ID;

        // 参数为本地图片路径
        String image = img_path;
        JSONObject res = client.addUser(uid, userInfo, groupId, image, options);
        return res;
    }

    public JSONObject update_user(String id, String img_path) {  // # 更新用户，需传入用户的ID，用户新的人脸图片的路径，返回数据包含唯一字段log_id，随机数
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        String uid = id;
        String userInfo = "";
        String groupId = GROUP_ID;

        // 参数为本地图片路径
        String image = img_path;
        JSONObject res = client.updateUser(uid, userInfo, groupId, image, options);
        return res;
    }

    public JSONObject delete_user(String id) {  // # 删除用户，需要传入待删除用户的ID
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        String uid = id;

        // 人脸删除
        JSONObject res = client.deleteUser(uid, options);
        return res;
    }
}
