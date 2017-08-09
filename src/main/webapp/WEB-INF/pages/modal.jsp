<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="trade-dialog" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form class="form-horizontal" data-bind="submit: trade().executeTrade">
                    <fieldset>
                        <legend>
                            <span data-bind="text: trade().action"></span>
                            <span data-bind="text: trade().currentRow().company"></span> Stock
                        </legend>
                    </fieldset>
                    <div class="form-group" data-bind="css: {error: trade().error()}">
                        <label class="col-sm-2 control-label" for="inputShares">Shares</label>
                        <div class="col-sm-10">
                            <input id="inputShares" class="form-control" type="text"
                                   data-bind="value: trade().sharesToTrade">
                            <span class="help-block" data-bind="text: trade().error">Please enter</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label class="checkbox"><input type="checkbox"
                                                               data-bind="checked: trade().suppressValidation">
                                    Suppress client-side validation</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary" type="submit">
                                <span data-bind="text: trade().action"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>