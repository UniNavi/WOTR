package com.danilketov.wotr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.wotr.R;
import com.danilketov.wotr.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> users = new ArrayList<>();
    private OnInfoUserClickListener onInfoUserClickListener;

    public AccountAdapter(OnInfoUserClickListener onInfoUserClickListener) {
        this.onInfoUserClickListener = onInfoUserClickListener;
    }

    public interface OnInfoUserClickListener {
        void onInfoUserClick (Account account);
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item, parent, false);

        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = users.get(position);
        holder.bind(account);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView nicknameTextView;
        private TextView textViewUserPercentWins;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            nicknameTextView = itemView.findViewById(R.id.text_view_nickname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Account account = users.get(getLayoutPosition());
                    onInfoUserClickListener.onInfoUserClick(account);

                }
            });
        }

        void bind(Account account) {
            nicknameTextView.setText(account.getNickname());
        }
    }

    // Добавляет данные из Activity
    public void addItems(List<Account> items) {
        users.addAll(items);
        notifyDataSetChanged();
    }

    // Удалаяет старые элементы в списке
    public void clearItems() {
        users.clear();
        notifyDataSetChanged();
    }
}
