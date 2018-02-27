package com.zjf.myself.codebase.thirdparty.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AItsuki on 2017/3/23.
 */

public class Repository {

    public List<Data> fakeDate() {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Data data = new Data();
            data.type = i % 6;
            switch (data.type) {
                case Type.LEFT_MENU:
                    data.content = "LEFT_MENU";
                    break;
                case Type.RIGHT_MENU:
                    data.content = "RIGHT_MENU";
                    break;
                case Type.LEFT_AND_RIGHT_MENU:
                    data.content = "LEFT_AND_RIGHT_MENU";
                    break;
                case Type.LEFT_LONG_MENU:
                    data.content = "LEFT_LONG_MENU";
                    break;
                case Type.RIGHT_LONG_MENU:
                    data.content = "RIGHT_LONG_MENU";
                    break;
                case Type.LEFT_AND_RIGHT_LONG_MENU:
                    data.content = "LEFT_AND_RIGHT_LONG_MENU";
                    break;
                default:
                    data.content = "DEFAULT";
                    break;
            }
            dataList.add(data);
        }

        return dataList;
    }
}
