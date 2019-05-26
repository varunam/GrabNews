package com.grab.news.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.grab.news.app.R;
import com.grab.news.app.repository.News;
import com.grab.news.app.ui.callbacks.NewsClickedCallbacks;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by varun.am on 2019-05-25
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();
    
    private List<News> newsList;
    private NewsClickedCallbacks newsClickedCallbacks;
    
    @Inject
    public RequestManager glide;
    
    public NewsAdapter(NewsClickedCallbacks newsClickedCallbacks){
        this.newsClickedCallbacks = newsClickedCallbacks;
    }
    
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final News news = newsList.get(position);
        Context context = holder.itemView.getContext();
        
        holder.title.setText(news.getTitle());
        holder.author.setText(news.getAuthor());
        holder.publishedDate.setText(news.getPublishedAt());
    
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getCircularProgressDrawable(context))
                .centerCrop();
        
        Glide.with(context)
                .load(news.getUrlToImage())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.newsImage);
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsClickedCallbacks.onNewsItemClicked(news, position);
            }
        });
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
        private CardView cardView;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            cardView = itemView.findViewById(R.id.cvNewsItemId);
            newsImage = itemView.findViewById(R.id.ivNewsImage);
            author = itemView.findViewById(R.id.tvNewsAuthor);
            publishedDate = itemView.findViewById(R.id.tvListItemDateTime);
            title = itemView.findViewById(R.id.tvNewsItemTitle);
            
        }
    }
    
    private CircularProgressDrawable getCircularProgressDrawable(Context context){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5F);
        circularProgressDrawable.setCenterRadius(30F);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
