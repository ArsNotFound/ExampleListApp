package me.arsnotfound.testapp;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.arsnotfound.testapp.databinding.ListItemBinding;

public class PlayerAdapter extends ArrayAdapter<Player> {
    private final LayoutInflater inflater;

    private final List<Player> players;

    private final SparseBooleanArray mCheckedMap = new SparseBooleanArray();

    public PlayerAdapter(Context context, List<Player> players) {
        super(context, 0, players);

        this.players = players;
        this.inflater = LayoutInflater.from(context);

        for (int i = 0; i < players.size(); i++) {
            mCheckedMap.put(i, false);
        }
    }

    void toggleChecked(int position) {
        mCheckedMap.put(position, !mCheckedMap.get(position));
        notifyDataSetChanged();
    }

    List<String> getCheckedItems() {
        List<String> checkedItems = new ArrayList<>();
        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(players.get(i).getName());
            }
        }
        return checkedItems;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Player getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //создаем представление для отдельного эемента списка
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ListItemBinding binding;
        if (convertView == null)
            binding = ListItemBinding.inflate(this.inflater, parent, false);
        else
            binding = ListItemBinding.bind(convertView);

        Player player = players.get(position);
        binding.name.setText(player.getName());
        binding.count.setText(String.format(Locale.getDefault(), "%d", player.getBall()));

        boolean checked = mCheckedMap.get(position);
        binding.selCheckbox.setChecked(checked);

        return binding.getRoot();
    }
}
