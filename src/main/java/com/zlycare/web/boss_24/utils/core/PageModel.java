package com.zlycare.web.boss_24.utils.core;

import java.util.List;

/**
 * Author : LinGuoDong
 * Create : 2016/4/11 17:43
 * Update : 2016/4/11 17:43
 * Descriptions :
 */
public class PageModel {

        //结果集
        private List<Object> list;

        //查询记录数
        private int totalRecords;

        //每页多少条数据
        private int pageSize;

        //第几页
        private int currentPage;

        /**
         * 总页数
         * @return
         */
        public int getTotalPages() {
            return (totalRecords + pageSize - 1) / pageSize;
        }

        /**
         * 取得首页
         * @return
         */
        public int getTopPageNo() {
            return 1;
        }

        /**
         * 上一页
         * @return
         */
        public int getPreviousPageNo() {
            if (currentPage <= 1) {
                return 1;
            }
            return currentPage - 1;
        }

        /**
         * 下一页
         * @return
         */
        public int getNextPageNo() {
            if (currentPage >= getBottomPageNo()) {
                return getBottomPageNo();
            }
            return currentPage + 1;
        }

        /**
         * 取得尾页
         * @return
         */
        public int getBottomPageNo() {
            return getTotalPages();
        }

        public List<Object> getList() {
            return list;
        }

        public void setList(List<Object> list) {
            this.list = list;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

