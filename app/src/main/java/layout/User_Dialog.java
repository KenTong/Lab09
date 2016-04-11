package layout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.opro.ken.lab09.R;


public class User_Dialog extends DialogFragment {
    private EditText m_et_username;

    public interface callback {
        void call (CharSequence username,int which);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_user__dialog, null);
        m_et_username = (EditText) view.findViewById(R.id.et_username);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence username = m_et_username.getText();
                                ((User_Dialog.callback) getActivity()).call(username, which);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((User_Dialog.callback)getActivity()).call(null,which);
                    }
                });
        return builder.create();
    }
}
