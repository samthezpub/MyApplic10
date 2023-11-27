package edu.itstep.myapplic10;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDAO contactDAO;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDAO = App.getInstance().getContactDAO();
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactAdapter = new ContactAdapter(contactDAO.getAll());
        recyclerView.setAdapter(contactAdapter);

        // Добавление контакта (переход на новую активность или фрагмент)
        Button btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Тут вызывай новую активность для добавления контакта
                // Или используй фрагмент для ввода данных и обновления списка
            }
        });

        // Добавление функционала для удаления контакта по свайпу
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Внутренний класс ContactAdapter
    private class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
        private List<Contact> contactList;

        public ContactAdapter(List<Contact> contactList) {
            this.contactList = contactList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Contact contact = contactList.get(position);
            holder.bind(contact);
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        // Внутренний класс ViewHolder
        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvFullName;
            private TextView tvPhone;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvFullName = itemView.findViewById(R.id.tvFullName);
                tvPhone = itemView.findViewById(R.id.tvPhone);
            }

            public void bind(Contact contact) {
                tvFullName.setText(contact.getFullName());
                tvPhone.setText(contact.getPhone());
            }
        }
    }

    // Внутренний класс SwipeToDeleteCallback
    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
        public SwipeToDeleteCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Contact deletedContact = contactAdapter.contactList.get(position);

            // Покажи AlertDialog для подтверждения удаления
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Подтверждение удаления")
                    .setMessage("Вы уверены, что хотите удалить контакт?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Удаление контакта из базы данных и обновление списка
                            contactDAO.delete(deletedContact);
                            contactAdapter.contactList.remove(position);
                            contactAdapter.notifyItemRemoved(position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Восстановление контакта после отмены удаления
                            contactAdapter.notifyItemChanged(position);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
