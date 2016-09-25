package com.blues.money_saver;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blues.money_saver.data.MoneyContract;

/**
 * Created by Blues on 25/09/2016.
 */

public class OverviewRecycleAdapter extends RecyclerView.Adapter<OverviewRecycleAdapter.OverviewRecycleViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final int recycleitems = 1;
    private Cursor mCursor;
    public OverviewRecycleAdapter(Context context)
    {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public OverviewRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new OverviewRecycleViewHolder(mLayoutInflater.inflate(R.layout.list_item_category, parent, false));

    }

    @Override
    public void onBindViewHolder(OverviewRecycleViewHolder holder, int position)
    {
        mCursor.moveToPosition(position);
        holder.mIncomeTextview.setText(mCursor.getString(mCursor.getColumnIndex(MoneyContract.SummaryEntry.COLUMN_SUMMARY_INCOME)));
        holder.mPayoutTextview.setText(mCursor.getString(mCursor.getColumnIndex(MoneyContract.SummaryEntry.COLUMN_SUMMARY_PAYOUT)));
        holder.mBalanceTextview.setText(mCursor.getString(mCursor.getColumnIndex(MoneyContract.SummaryEntry.COLUMN_SUMMARY_BALANCE)));


    }

    @Override
    public int getItemCount()
    {
        if(mCursor ==null) return 0;
        return mCursor.getCount();
    }

    public static class OverviewRecycleViewHolder extends RecyclerView.ViewHolder {

        TextView mIncomeTextview;
        TextView mPayoutTextview;
        TextView mBalanceTextview;


        OverviewRecycleViewHolder(View view)
        {
            super(view);
            mIncomeTextview = (TextView) view.findViewById(R.id.item_income);
            mPayoutTextview = (TextView) view.findViewById(R.id.item_payout);
            mBalanceTextview = (TextView) view.findViewById(R.id.item_balance);
        }
    }

    public void swapCursor(Cursor newCursor)
    {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
