package pgreze.pagerinception;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** Fragment with text + previous / next navigation buttons */
public class PageFragment extends Fragment {
    private static final String TEXT_KEY = "text_key";
    private static final String COLOR_KEY = "color_key";

    public interface PageContext {
        void previous(Fragment fragment);
        void next(Fragment fragment);
    }

    public static PageFragment build(String text, int color) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle(2);
        args.putString(TEXT_KEY, text);
        args.putInt(COLOR_KEY, color);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getArguments().getInt(COLOR_KEY));

        ((TextView) view.findViewById(R.id.txt)).setText(getArguments().getString(TEXT_KEY));
        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParent().previous(PageFragment.this);
            }
        });
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParent().next(PageFragment.this);
            }
        });
    }

    public PageContext getParent() {
        if (getParentFragment() != null) {
            return (PageContext) getParentFragment();
        } else {
            return (PageContext) getActivity();
        }
    }
}
