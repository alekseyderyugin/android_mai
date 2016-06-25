package ozone.mai_2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Ozone on 28.05.2016.
 */

public class DefaultTreeHolder extends TreeNode.BaseNodeViewHolder<DefaultTreeHolder.IconTreeItem> implements Serializable {
    private String text;
    private Context currentContext;
    private Window currentWindow;
    public int index = 1;
    public DefaultTreeHolder(Context context, Window window) {
        super(context);
        currentContext = context;
        currentWindow = window;
    }
    public int id;
    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final AndroidTreeView tree = this.getTreeView();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.default_tree_item, null, false);
        final TextView add = (TextView)view.findViewById(R.id.add_criterion);

        Random r = new Random();

        id = (r.nextInt(80 - 65) + 65);
        view.setId(id);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CriterionsTreeHolder.IconTreeItem crItemIcon = new CriterionsTreeHolder.IconTreeItem();
                TreeNode newNode = new TreeNode(crItemIcon).setViewHolder(new CriterionsTreeHolder(currentContext, currentWindow));
                node.addChildren(newNode);
                EditText criterionNameEdit = (EditText)newNode.getViewHolder().getView().findViewById(R.id.criterion_add_text);
                criterionNameEdit.setText(criterionNameEdit.getText() +" "+index);
                index++;
                tree.expandLevel(node.getLevel());
            }
        });
        /*text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tree.toggleNode(node);
            }
        });*/
        return view;
    }
    public static class IconTreeItem {
        public int icon;
        public String text;
    }
}

