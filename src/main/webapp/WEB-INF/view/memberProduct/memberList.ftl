<form id="pagerForm" class="pagerForm_member_manage" method="post" action="/member/list/get">
    <input type="hidden" name="name" value="${(memberBean.name)!}"/>
    <input type="hidden" name="docChatNum" value="${(memberBean.docChatNum)!}"/>
    <input type="hidden" name="phoneNum" value="${(memberBean.phoneNum)!}"/>
    <input type="hidden" name="pageNum" value="${page.pageNo!}"/>
    <input type="hidden" name="numPerPage" value="${page.size!}"/>
</form>

<script>
    /* 重置表单 */
    function resetForm_member() {
        //重置非隐藏域
        $('#member_manage input').val("");
        // 隐藏域 重置
        $(".pagerForm_member_manage input").val("");
    }
</script>

<div class="pageHeader">
    <form id="member_manage" onsubmit="return navTabSearch(this);"
          action="/member/list/get"
          method="post"
          onreset="$(this).find('select.combox').comboxReset()">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td style="float: none">会员姓名：<input type="text" name="name"
                                                        value="${(memberBean.name)!}"/>
                    </td>
                    <td>会员热线号：<input type="text" name="docChatNum"
                                     value="${(memberBean.docChatNum)!}"/>
                    </td>

                    <td>会员手机号：<input type="text" name="phoneNum"
                                     value="${(memberBean.phoneNum)!}"/></td>


                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                            <#--<button type="reset">重置</button>-->
                                <button type="button" onclick="resetForm_member()">重置</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">

    </div>

    <table class="table" width="100%" style="table-layout:fixed;word-break:break-all;" layoutH="138">
        <thead>
        <tr>
            <th width="60">序号</th>
            <th width="100">会员姓名</th>
            <th width="100">性别</th>
            <th width="95">会员热线号</th>
            <th width="160">会员手机号</th>
            <th width="105">高级会员总额度</th>
            <th width="105">高级会员剩余额度</th>
        <#--<th width="105">VIP会员总额度</th>-->
        <#--<th width="105">VIP会员剩余额度</th>-->
        </tr>
        </thead>
    <#if page?? && page.data??>
        <#if page.data?? && (page.data?size > 0)>
            <#list page.data as member>
                <tr id="${(member.id)!}" target="sid_user"
                    rel="${(member.id)!}">
                    <td>${(page.pageNo-1)*page.size+member_index+1}</td>
                    <td>${(member.name)!}</td>
                    <td>${(member.sex)!}</td>
                    <td>${(member.docChatNum)!}</td>
                    <td>${(member.phoneNum)!}</td>
                    <td>${(member.totalVal)!}</td>
                    <td>${(member.balance)!}</td>
                <#--<td>${(member.advancedTotalVal)!}</td>-->
                <#--<td>${(member.advancedBalance)!}</td>-->
                </tr>
            </#list>
        </#if>
    </#if>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select id="" class="combox" name="numPerPage"
                    onchange="navTabPageBreak({numPerPage:this.value})">
            <#--<#assign configArr = [1,10,20,50,100]>-->
            <#assign configArr_2 = [20,50,100]>
            <#list configArr_2 as item_2>
                <option value="${item_2}" <#if (item_2==page.size)>selected="selected"</#if>>${item_2}</option>
            </#list>
            </select>
            <span>条，共${(page.total)!}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.total!}" numPerPage="${page.size!}"
             pageNumShown="10"
             currentPage="${page.pageNo!}"></div>
    </div>
</div>
