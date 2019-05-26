package com.grab.news.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grab.news.app.R;
import com.grab.news.app.repository.News;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by varun.am on 2019-05-25
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    
    private List<News> newsList;
    
    @Inject
    public RequestManager glide;
    
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        
        holder.title.setText(news.getTitle());
        holder.author.setText(news.getAuthor());
        holder.publishedDate.setText(news.getPublishedAt());
        
        Glide.with(holder.itemView.getContext())
                .load(news.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.newsImage);
    }
    
    @Override
    public int getItemCount() {
        if (newsList != null && newsList.size() > 0)
            return newsList.size();
        else
            return 0;
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        
        private ImageView newsImage;
        private TextView author, publishedDate, title;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            newsImage = itemView.findViewById(R.id.ivNewsImage);
            author = itemView.findViewById(R.id.tvNewsAuthor);
            publishedDate = itemView.findViewById(R.id.tvListItemDateTime);
            title = itemView.findViewById(R.id.tvNewsItemTitle);
            
        }
    }
}
