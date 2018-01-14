package com.br.tomei.util;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.tomei.R;
import com.br.tomei.model.Breja;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerTesteViewHolder> {
 
    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<Breja> mList;
 
    public RecyclerAdapter(Context ctx, List<Breja> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public RecyclerTesteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemlist_itemdalista, viewGroup, false);
        return new RecyclerTesteViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(RecyclerTesteViewHolder viewHolder, int i) {
        Breja item = mList.get(i);

        viewHolder.setIsRecyclable(false);

        viewHolder.viewID.setText(viewHolder.viewID.getText().toString()+": "+item.getId());
        viewHolder.viewNome.setText(viewHolder.viewNome.getText().toString()+": "+item.getNome());
        viewHolder.viewTipo.setText(viewHolder.viewTipo.getText().toString()+": "+item.getTipo());
        viewHolder.viewDescricao.setText(viewHolder.viewDescricao.getText().toString()+": "+item.getDescricao());
 
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
 
 
    protected class RecyclerTesteViewHolder extends RecyclerView.ViewHolder {
 
        protected TextView viewNome, viewTipo, viewDescricao, viewID;
        protected FloatingActionButton btnRemoverBreja;
 
        public RecyclerTesteViewHolder(final View itemView) {
            super(itemView);

            viewID = (TextView) itemView.findViewById(R.id.textview_id);
            viewNome = (TextView) itemView.findViewById(R.id.textview_nome);
            viewTipo = (TextView) itemView.findViewById(R.id.textview_tipo);
            viewDescricao = (TextView) itemView.findViewById(R.id.textview_descricao);
            btnRemoverBreja = (FloatingActionButton) itemView.findViewById(R.id.btnRemoverBreja);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));
                }
            });


            btnRemoverBreja.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    clickRecyclerViewInterface.onCloseButton(mList.get(getLayoutPosition()));
                }
            });
        }
    }
}