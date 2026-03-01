package njust.dzh.dianying.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import njust.dzh.dianying.Bean.Cart;
import njust.dzh.dianying.DataBase.CartDao;
import njust.dzh.dianying.Fragment.CartFragment;
import njust.dzh.dianying.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context mContext;
    private List<Cart> mCartList;
    private CartDao cartDao;
    private Handler handler = new Handler();

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView cartImage;
        TextView cartName;
        TextView cartPrice;
        TextView cartNum;
        TextView cartAdd;
        TextView cartMinus;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            cartImage = itemView.findViewById(R.id.cart_image);
            cartName = itemView.findViewById(R.id.cart_name);
            cartPrice = itemView.findViewById(R.id.cart_price);
            cartNum = itemView.findViewById(R.id.cart_num);
            cartAdd = itemView.findViewById(R.id.cart_add);
            cartMinus = itemView.findViewById(R.id.cart_minus);
        }
    }
    //
    public CartAdapter(List<Cart> cartList) {
        mCartList = cartList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        // Long press to delete card layout
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                Cart cart = mCartList.get(position);
                cartDao = new CartDao(mContext);
                // Prompt box, OK button and Cancel button
                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete this product?？")
                        .setIcon(R.drawable.ic_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cartDao.openDB();
                                cartDao.deleteCart(cart);
                                cartDao.closeDB();
                                Toast.makeText(mContext, "Deletion successful! Please refresh the page.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(mContext, "Deletion canceled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            }
        });

        //
        holder.cartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Cart cart = mCartList.get(position);
                cartDao = new CartDao(mContext);
                cartDao.openDB();
                cartDao.addCartNum(cart);
                String curPrice = cartDao.getPrice(cart);
                cartDao.closeDB();
                String curCartNum = holder.cartNum.getText().toString();
                int curNum = Integer.parseInt(curCartNum);
                holder.cartNum.setText("" + (curNum + 1));
                holder.cartPrice.setText("" + curPrice);
                Toast.makeText(mContext, "increase success", Toast.LENGTH_SHORT).show();
            }
        });
        //
        holder.cartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Cart cart = mCartList.get(position);
                //
                cartDao = new CartDao(mContext);
                cartDao.openDB();
                cartDao.minusCartNum(cart);
                String curPrice = cartDao.getPrice(cart);
                cartDao.closeDB();

                String curCartNum = holder.cartNum.getText().toString();
                int curNum = Integer.parseInt(curCartNum);
                if (curNum > 1) {
                    holder.cartNum.setText("" + (curNum - 1));
                    holder.cartPrice.setText(curPrice);
                    Toast.makeText(mContext, "reduce success", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cart cart = mCartList.get(position);
        holder.cartName.setText(cart.getName());
        Glide.with(mContext).load(cart.getImageId()).into(holder.cartImage);
        holder.cartPrice.setText(cart.getPrice());
        holder.cartNum.setText(""+cart.getNum());
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    // Handler封装成发送消息的方法
    public void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = CartFragment.UPDATE_CART;
                handler.sendMessage(message);
            }
        }).start();
    }
}


