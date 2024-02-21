package me.arsnotfound.testapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import me.arsnotfound.testapp.databinding.ListItemBinding;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private final List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(layoutInflater, parent, false);
        PlayerViewHolder holder = new PlayerViewHolder(binding);

        binding.getRoot().setOnClickListener(v -> onItemClick(holder));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.binding.name.setText(player.getName());
        holder.binding.count.setText(String.format(Locale.getDefault(), "%d", player.getBall()));
    }

    private void onItemClick(PlayerViewHolder holder) {
        Player player = players.get(holder.getAbsoluteAdapterPosition());
        player.setChecked(!player.isChecked());
        holder.binding.selCheckbox.setChecked(player.isChecked());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public PlayerViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
