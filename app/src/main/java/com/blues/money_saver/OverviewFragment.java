package com.blues.money_saver;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blues.money_saver.data.MoneyContract;

/**
 * Created by Blues on 25/09/2016.
 */

public class OverviewFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    String monthindex;
    private OverviewRecycleAdapter mOverviewAdapter;
    private static final int CURSOR_LOADER_ID = 0;
    private static final int Summary_LOADER = 0;

    public OverviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        monthindex = getArguments().getString("monthFragment");
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_summary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        mOverviewAdapter = new OverviewRecycleAdapter(getActivity());
        recyclerView.setAdapter(mOverviewAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(Summary_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri moneyUri = MoneyContract.SummaryEntry.CONTENT_URI;
        String select_month = MoneyContract.SummaryEntry.COLUMN_SUMMARY_MONTH;

        return new CursorLoader(getActivity(),
                moneyUri,
                null,
                select_month + "=?",
                new String[]{monthindex},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        mOverviewAdapter.swapCursor(data);

    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mOverviewAdapter.swapCursor(null);
    }

}
