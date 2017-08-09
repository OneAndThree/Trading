<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tradeOrderDetail-dialog" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form class="form-horizontal" data-bind="submit: tradeOrderDetail().executeTrade">
                    <fieldset>
                        <legend>
                            <span data-bind="text: tradeOrderDetail().action"></span>
                            <span data-bind="text: tradeOrderDetail().currentRow().company"></span> Stock
                        </legend>
                    </fieldset>
                    <div class="form-group" data-bind="css: {error: tradeOrderDetail().error()}">
                        <label class="col-sm-2 control-label" for="inputShares">Shares</label>
                        <div class="col-sm-10">
                            <input id="inputShares" class="form-control" type="text"
                                   data-bind="value: tradeOrderDetail().sharesToTrade">
                            <span class="help-block" data-bind="text: tradeOrderDetail().error">Please enter</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox"
                                                               data-bind="checked: tradeOrderDetail().suppressValidation">
                                    Suppress client-side validation</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary" type="submit">
                                <span data-bind="text: tradeOrderDetail().action"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>