<html>
<head>
    <meta charset="utf-8">
    <title>奇怪的日常</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="description" content="为奇怪的日常提交数据。今天工作好认真，奖励自己一只鸡腿！">
    <meta property="og:type" content="webpage">
    <meta property="og:title" content="奇怪的日常">
    <meta property="og:description" content="为奇怪的日常提交数据。今天工作好认真，奖励自己一只鸡腿！">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body data-locale="zh-CN">
<div>
    <form id="answer" action="/answer" accept-charset="UTF-8" method="post">
        <div class="form-header container-fluid">
            <div class="row">
                <h1 class="form-title col-md-12">
                    心情收集器
                </h1>
                <div class="form-description col-md-12">
                    <p>今天工作好认真，奖励自己一只鸡腿！</p>
                </div>
            </div>
        </div>
        <div class="form-content container-fluid">
            <div class="row">

                <div class="fields clearfix">
                    <div class="field field-image-check-box col-sm-12 required" data-api-code="field_1" data-type="ImageCheckBox" data-label="今天心情怎么样？" data-validations="[&quot;Presence&quot;,&quot;Length&quot;]" data-minimum-length="1" data-maximum-length="1" data-length-hint="选1项">
                        <div class="form-group">
                            <div class="field-label-container" onclick="">
                                <label class="field-label" for="entry_field_1">
                                    今天心情怎么样？
                                </label>
                            </div>
                            <div class="field-content">

                                <div class="choices image-choices">
                                    <label><input name="mood" type="radio" value="0" /><img src="/1supergood.png"  alt="超棒" />超棒！</label><p></p>
                                    <label><input name="mood" type="radio" value="1" /><img src="/2good.png" alt="不错" />不错！</label><p></p>
                                    <label><input name="mood" type="radio" value="2" /><img src="/3hmmmmm.png" alt="无奈" />无奈!</label><p></p>
                                    <label><input name="mood" type="radio" value="3" /><img src="/4bad.png" alt="不好" />不好!</label><p></p>
                                    <label><input name="mood" type="radio" value="4" /><img src="/5superbad.png" alt="炒鸡差" />炒鸡差！</label><p></p>
                                </div>
                                <label>你的名字</label><p></p>
                                <div class="choices image-choices">
                                    <label><input name="aid" type="radio" value="1" />ws </label>
                                    <label><input name="aid" type="radio" value="2" />hm </label>
                                    <label><input name="aid" type="radio" value="3" />ql</label>
                                    <label><input name="aid" type="radio" value="4" />志辉</label>
                                    <label><input name="aid" type="radio" value="5" />lh</label>
                                    <label><input name="aid" type="radio" value="6" />jy</label>
                                    <label><input name="aid" type="radio" value="7" />xz</label>
                                    <label><input name="aid" type="radio" value="8" />lx</label>
                                    <label><input name="aid" type="radio" value="9" />my</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                <div
            </div>
        </div>

        <div>
            <p></p>
            <input type="submit" value="提交">
            <#--<input type="button" id="ajaxBtn" value="AJAX提交" />-->
            <#--<input type="button" id="jqueryBtn" value="jQuery提交" />-->
            <#--<input type="button" id="jsBtn" value="JS提交" />-->
            <#--<input type="submit" value="onSubmit提交" />-->
        </div>

    </form>

    <footer class="published text-center">
        <a target="_blank" class="powered-by " href="https://www.dianrong.com" style="opacity: 1;">
            <p>
                <span>Powered by </span>
                <i class="gd-icon-logo powered-logo"></i><span> Backend Lender, Thanks for UX</span>
            </p>
        </a>
    </footer>
</div>

<script type="text/javascript">
    $(function() {
        //ajax提交
        $("#ajaxBtn").click(function() {
            var params = $("#answer").serialize();
            $.ajax( {
                        type : "POST",
                        url : "answer",
                        processData:false,
                        contentType:false,
                        data : params,
                        success : function(msg) {
                            alert("success: " + msg);
                        }
                    });
        })

        //jQuery提交
        $("#jqueryBtn").click(function(){
            $("#answer").submit();
        })

        //js提交
        $("#jsBtn").click(function(){
            document.myform.action="RegisterAction.action";
            document.myform.submit();
        })
    })
    function sumbitTest(){
        return true; //return false则不会提交
    }
</script>

</body>
</html>
