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
                        <label class="col-md-offset-2 col-md-2 control-label" for="inputShares">Shares</label>
                        <div class="col-md-5">
                            <input id="inputShares" class="form-control" type="text"
                                   data-bind="value: trade().sharesToTrade">
                            <span class="help-block" data-bind="text: trade().error">Please enter</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-offset-2 col-md-2 control-label" for="selectType">Type</label>
                        <div class="col-md-7">
                        <select class="col-md-8 input-sm" id="selectType">
                            <option><a href="#">Mrkt</a></option>
                            <option><a href="#">IOC</a></option>
                            <option><a href="#">FOK</a></option>
                            <option><a href="#">GTC</a></option>
                        </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button class="btn" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary col-md-offset-2" type="submit">
                                <span data-bind="text: trade().action"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>