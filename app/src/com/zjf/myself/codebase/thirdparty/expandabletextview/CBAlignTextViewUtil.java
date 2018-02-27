package com.zjf.myself.codebase.thirdparty.expandabletextview;

/**
 * 文本工具
 * Created by yuedong.lyd on 11/30/15.
 */
public class CBAlignTextViewUtil {

    /**
     * 将中文标点替换为英文标点
     *
     * @return 替换后的文本
     */
    public static String replacePunctuation(String text) {
        text = text.replace('，', ',').replace('。', '.').replace('【', '[').replace('】', ']')
                .replace('？', '?').replace('！', '!').replace('（', '(').replace('）', ')').replace
                        ('“', '"').replace('”', '"');
        return text;
    }
}
