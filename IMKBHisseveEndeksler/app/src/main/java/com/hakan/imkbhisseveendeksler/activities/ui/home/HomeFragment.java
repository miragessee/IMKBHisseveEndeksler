package com.hakan.imkbhisseveendeksler.activities.ui.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.filter.Filter;
import com.evrencoskun.tableview.filter.FilterChangedListener;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.hakan.imkbhisseveendeksler.R;
import com.hakan.imkbhisseveendeksler.activities.StockDetailActivity;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.TableViewAdapter;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.TableViewModel;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.holder.ColumnHeaderViewHolder;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.model.Cell;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.model.ColumnHeader;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.model.RowHeader;
import com.hakan.imkbhisseveendeksler.activities.ui.tableview.popup.ColumnHeaderLongPressPopup;
import com.hakan.imkbhisseveendeksler.base.BaseIntent;
import com.hakan.imkbhisseveendeksler.base.BaseUtils;
import com.hakan.imkbhisseveendeksler.base.Globals;
import com.hakan.imkbhisseveendeksler.base.StringEncrypt;
import com.hakan.imkbhisseveendeksler.callbacks.StocksCallback;
import com.hakan.imkbhisseveendeksler.controllers.StocksController;
import com.hakan.imkbhisseveendeksler.databinding.FragmentHomeBinding;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.Stock;
import com.hakan.imkbhisseveendeksler.models.stocks.responses.StocksResponse;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeFragment extends Fragment implements ITableViewListener {

    private TableViewAdapter tableViewAdapter;

    private StocksController stocksController = StocksController.getInstance();

    FragmentHomeBinding binding;

    private KProgressHUD hud;

    private Filter tableViewFilter;

    public static String periodValue = "all";
    public static boolean refresh = false;

    private List<Stock> stockList;

    private List filteredCellItems;

    private List<ColumnHeader> mColumnHeaderModelList;
    private List<RowHeader> mRowHeaderModelList;
    private List<List<Cell>> mCellModelList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        binding = FragmentHomeBinding.bind(root);

        initializeTableView();

        tableViewFilter = new Filter(binding.myTableView);

        binding.editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_NEXT) {
                    binding.myTableView.getSelectionHandler().clearSelection();
                    filterWholeTable(binding.editTextSearch.getText().toString());
                }
                return false;
            }
        });

        binding.myTableView.getFilterHandler().addFilterChangedListener(new FilterChangedListener() {
            @Override
            public void onFilterChanged(List filteredCellItems, List filteredRowHeaderItems) {
                super.onFilterChanged(filteredCellItems, filteredRowHeaderItems);
                HomeFragment.this.filteredCellItems = filteredCellItems;
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (filteredCellItems == null) {
            try {
                String period = StringEncrypt.encrypt(Globals.AES_KEY, Globals.AES_IVX, periodValue);

                hud = KProgressHUD.create(getContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();

                stocksController.postStockList(period, new StocksCallback() {
                    @Override
                    public void onSuccess(StocksResponse stocksResponse) {
                        setStockList(stocksResponse.getStocks());
                        stockList = stocksResponse.getStocks();
                        hud.dismiss();
                    }

                    @Override
                    public void onError() {
                        hud.dismiss();
                        BaseUtils.showToast(getContext(), "StockList başarısız.");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (refresh) {
            try {
                String period = StringEncrypt.encrypt(Globals.AES_KEY, Globals.AES_IVX, periodValue);

                binding.editTextSearch.setText("");
                if (filteredCellItems != null) {
                    filteredCellItems.clear();
                }
                binding.myTableView.getSelectionHandler().clearSelection();

                hud = KProgressHUD.create(getContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();

                stocksController.postStockList(period, new StocksCallback() {
                    @Override
                    public void onSuccess(StocksResponse stocksResponse) {
                        setStockList(stocksResponse.getStocks());
                        stockList = stocksResponse.getStocks();
                        refresh = false;
                        hud.dismiss();
                    }

                    @Override
                    public void onError() {
                        hud.dismiss();
                        BaseUtils.showToast(getContext(), "StockList başarısız.");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeTableView() {

        binding.myTableView.setRowHeaderWidth(120);

        TableViewModel tableViewModel = new TableViewModel();

        tableViewAdapter = new TableViewAdapter(tableViewModel);

        binding.myTableView.setAdapter(tableViewAdapter);
        binding.myTableView.setTableViewListener(this);

    }

    public void setStockList(List<Stock> stockList) {
        generateListForTableView(stockList);

        tableViewAdapter.setAllItems(mColumnHeaderModelList, mRowHeaderModelList, mCellModelList);
    }

    public void generateListForTableView(List<Stock> stocks) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(stocks);
        mRowHeaderModelList = createRowHeaderList(stocks.size());
    }

    private List<RowHeader> createRowHeaderList(int size) {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new RowHeader(String.valueOf(i), String.valueOf(i + 1)));
        }
        return list;
    }

    private List<ColumnHeader> createColumnHeaderModelList() {
        List<ColumnHeader> list = new ArrayList<>();

        list.add(new ColumnHeader(String.valueOf(0),"Sembol"));
        list.add(new ColumnHeader(String.valueOf(1),"Fiyat"));
        list.add(new ColumnHeader(String.valueOf(2),"Fark"));
        list.add(new ColumnHeader(String.valueOf(3),"Hacim"));
        list.add(new ColumnHeader(String.valueOf(4),"Alış"));
        list.add(new ColumnHeader(String.valueOf(5),"Satış"));
        list.add(new ColumnHeader(String.valueOf(6),"Değişim"));

        return list;
    }

    private List<List<Cell>> createCellModelList(List<Stock> stockList) {
        List<List<Cell>> lists = new ArrayList<>();

        for (int i = 0; i < stockList.size(); i++) {
            Stock stock = stockList.get(i);

            List<Cell> list = new ArrayList<>();

            try {
                list.add(new Cell("1-" + i, StringEncrypt.decrypt(Globals.AES_KEY, Globals.AES_IVX, stock.getSymbol())));          // "Sembol"
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(new Cell("2-" + i, stock.getPrice()));        // "Fiyat"
            list.add(new Cell("3-" + i, stock.getDifference()));    // "Fark"
            list.add(new Cell("4-" + i, stock.getVolume()));       // "Hacim"
            list.add(new Cell("5-" + i, stock.getBid()));   // "Alış"
            list.add(new Cell("6-" + i, stock.getOffer()));      // "Satış"
            list.add(new Cell("7-" + i, stock.getIsUp()));         // "Değişim"

            lists.add(list);
        }

        return lists;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void filterWholeTable(String filterKeyword) {
        if (filteredCellItems != null) {
            filteredCellItems.clear();
        }
        tableViewFilter.set(filterKeyword);
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder viewHolder, int column, int row) {
        Cell cell = null;
        if (filteredCellItems != null)  {
            if (!filteredCellItems.isEmpty()) {
                cell = ((Cell) ((ArrayList) filteredCellItems.get(row)).get(0));
            }
            else {
                cell = (Cell) binding.myTableView.getAdapter().getCellItem(0, row);
            }
        }
        else {
            cell = (Cell) binding.myTableView.getAdapter().getCellItem(0, row);
        }
        String sembol = String.valueOf(cell.getData());
        Stock stock = findUsingIterator(sembol,stockList);
        if (stock != null)
        {
            StockDetailActivity.stockId = stock.getId();
            try {
                StockDetailActivity.stockID_AES = StringEncrypt.encrypt(Globals.AES_KEY,Globals.AES_IVX, String.valueOf(stock.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BaseIntent.baseIntent(getContext(),StockDetailActivity.class);
    }

    @Override
    public void onCellDoubleClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    public Stock findUsingIterator(String sembol, List<Stock> stockList) {
        Iterator<Stock> iterator = stockList.iterator();
        while (iterator.hasNext()) {
            Stock stock = iterator.next();
            try {
                if (StringEncrypt.decrypt(Globals.AES_KEY, Globals.AES_IVX, stock.getSymbol()).equals(sembol)) {
                    return stock;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder viewHolder, int column) {
        if (viewHolder != null && viewHolder instanceof ColumnHeaderViewHolder) {

            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) viewHolder, binding.myTableView);

            popup.show();
        }
    }

    @Override
    public void onColumnHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int column) {
        if (viewHolder != null && viewHolder instanceof ColumnHeaderViewHolder) {

            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) viewHolder, binding.myTableView);

            popup.show();
        }
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder viewHolder, int row) {

    }

    @Override
    public void onRowHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int row) {

    }
}