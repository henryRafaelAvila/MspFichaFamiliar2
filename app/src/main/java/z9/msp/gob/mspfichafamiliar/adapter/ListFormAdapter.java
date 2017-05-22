package z9.msp.gob.mspfichafamiliar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import z9.msp.gob.mspfichafamiliar.R;
import z9.msp.gob.persistencia.entity.Formulario;

/**
 * Created by henry on 4/2/2017.
 */

public class ListFormAdapter extends ArrayAdapter<Formulario> {
    public ListFormAdapter(Context context, List<Formulario> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        // ¿Ya se infló este view?
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            convertView = inflater.inflate(
                    R.layout.list_form_item,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.company = (TextView) convertView.findViewById(R.id.tv_company);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lead actual.
        Formulario formulario = getItem(position);

        // Setup.
        holder.name.setText(formulario.getNombre());
        holder.title.setText(formulario.getDescripcion());
        holder.company.setText(formulario.getZona());
        Glide.with(getContext()).load(R.drawable.home).into(holder.avatar);

        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView title;
        TextView company;
    }
}
