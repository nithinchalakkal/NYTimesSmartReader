package com.smartreader.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smartreader.Controller.CommonFunction;
import com.smartreader.SmartReaderApplication;
import com.smartreader.R;
import com.smartreader.activity.MainActivity;
import com.smartreader.adapter.ArticlesAdapter;
import com.smartreader.model.Response;
import com.smartreader.network.NetworkError;
import com.smartreader.network.Service;
import com.smartreader.utils.SmartReaderProgressbar;

import javax.inject.Inject;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */

public class ArticlesFragment extends Fragment {

    @Inject
    Service service;
    private SmartReaderProgressbar smartReaderProgressbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartReaderApplication smartReaderApplication;
    TextView tv_copyright;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /* injecting dependency */
        smartReaderApplication = (SmartReaderApplication) getActivity().getApplication();
        (smartReaderApplication).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        tv_copyright=(TextView)view.findViewById(R.id.tv_copyright);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(CommonFunction.checkConnection(getActivity())==true) {

            smartReaderProgressbar = new SmartReaderProgressbar(getActivity());
            smartReaderProgressbar.showDialog();

            service.getBaseURL(new Service.ResponseCallback<Response>() {
                @Override
                public void onSuccess(Response response) {
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);

                    mAdapter = new ArticlesAdapter(response, (MainActivity) getActivity(), getFragmentManager());
                    recyclerView.setAdapter(mAdapter);
                    smartReaderProgressbar.dismissDialog();
                    tv_copyright.setText(response.getCopyright());
                }

                @Override
                public void onError(NetworkError networkError) {
                    smartReaderProgressbar.dismiss();
                    CommonFunction.Toast(getActivity().findViewById(android.R.id.content), networkError.getMessage(), "#FF0000");
                }
            });
        }else {
            CommonFunction.Toast(getActivity().findViewById(android.R.id.content), getResources().getString(R.string.NetworkIssue), "#FF0000");

        }




    }

}
