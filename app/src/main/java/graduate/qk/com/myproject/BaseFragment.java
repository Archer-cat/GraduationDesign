package graduate.qk.com.myproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/tab9/19.
 * 重写fragment的基类,懒加载
 */

public class BaseFragment extends Fragment {
    protected boolean isViewInitiated;//标识已经触发过懒加载界面
    protected boolean isVisibleToUser;//标识用户可见fragment
    protected boolean isDataInitiated;// 标识已经触发过懒加载数据
    private Fragment fragment;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    if (savedInstanceState == null) {
            // Instantiate fragment
        } else {
            // Restore the fragment's instance
            fragment = getSupportFragmentManager().getFragment(
                    savedInstanceState, "fragment");
        }*/
        fetchData();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
        //fetchData();
    }

    /*fetchData（）里做网络请求或者其他耗时操作*/
    public void fetchData() {

    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }
    /*这里就是预留了强制刷新～如果需要强制刷新～可以先调用prepareFetchData，传true*/
    public boolean prepareFetchData(boolean forceUpdate) {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    /*重新回到打开过的Fragment，它若是销毁了会重新调用fetchData()*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDataInitiated=false;
    }


}
