package com.example.ejemplochat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.ejemplochat.R;
import com.example.ejemplochat.model.UserModel;

public class UserAdapter extends ArrayAdapter<UserModel> {
    private final ChatButtonClickListener chatButtonClickListener;
    private Context context;
    private List<UserModel> users;


    // Constructor que toma un contexto, una lista de usuarios y un listener para el botón "Chatear"
    public UserAdapter(Context context, List<UserModel> users, ChatButtonClickListener listener) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
        this.chatButtonClickListener = listener;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserModel user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        Button buttonChat = convertView.findViewById(R.id.buttonChat);

        if (user != null) {
            textViewName.setText(user.getName());
            textViewEmail.setText(user.getEmail());

            // Uso de una clase interna no estática en lugar de una lambda o clase anónima
            buttonChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chatButtonClickListener != null) {
                        chatButtonClickListener.onChatButtonClick(user);
                    }
                }
            });
        }

        return convertView;
    }

    // Mover la interfaz fuera de la clase UserAdapter para evitar problemas con Java 8
    public interface ChatButtonClickListener {
        void onChatButtonClick(UserModel user);
    }
}

// Si tienes alguna clase o código que estaba generando el error de miembros estáticos en clases internas no estáticas, verifica que no haya más miembros estáticos en clases no estáticas. Si los había y eran necesarios, los puedes trasladar fuera o hacer la clase estática.

