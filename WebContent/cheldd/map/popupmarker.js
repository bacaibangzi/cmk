PopupMarker.IS_BAIDU_MAP = typeof BMap !=='undefined';
PopupMarker.YPOS = 0;
PopupMarker.LEFT_TOP_HEIGHT = 7;
PopupMarker.YPOS2 = PopupMarker.YPOS + PopupMarker.LEFT_TOP_HEIGHT;
PopupMarker.POPUP_TBL = {
		leftTop : {"left" : 0,"top" : PopupMarker.YPOS,"width" : 19,"height" : PopupMarker.LEFT_TOP_HEIGHT},
		leftTopFill : {"left" : 16,"top" : 3,"width" : 4,"height" : 4},
		rightTop : {"left" : 19,"top" : PopupMarker.YPOS,"width" : 10,"height" : PopupMarker.LEFT_TOP_HEIGHT},
		rightTopImg : {"left" : -125,"top" : 0,"width" : 10,"height" : PopupMarker.LEFT_TOP_HEIGHT},
		centerTopFill : {"left" : 19,"top" : PopupMarker.YPOS,"width" : 0,"height" : PopupMarker.LEFT_TOP_HEIGHT},
		leftBody : {"left" : 11,"top" : PopupMarker.YPOS2,"width" : 8,"height" : 0},
		centerBodyFill : {"left" : 19,"top" : PopupMarker.YPOS2,"width" : 40,"height" : 15},
		rightBody : {"left" : 19,"top" : PopupMarker.YPOS2,"width" : 9,"height" : 0},
		leftBottom : {"left" : 0,"top" : PopupMarker.YPOS2,"width" : 20,"height" : 21},
		leftBottomImg : {"left" : 0,"top" : -13,"width" : 20,"height" : 21},
		leftBottomFill : {"left" : 16,"top" : 0,"width" : 4,"height" : 6},
		rightBottom : {"left" : 19,"top" : PopupMarker.YPOS2,"width" : 10,"height" : PopupMarker.LEFT_TOP_HEIGHT},
		rightBottomImg : {"left" : -125,"top" : -13,"width" : 10,"height" : PopupMarker.LEFT_TOP_HEIGHT}
};
PopupMarker.prototype =PopupMarker.IS_BAIDU_MAP?(new BMap.Overlay()):(new google.maps.OverlayView());
 
function PopupMarker(opts) {
    this.ICON_WIDTH = opts.ICON_WIDTH||12;
    this.ICON_HEIGHT =opts.ICON_HEIGHT|| 20;
    this.map_ = opts.map;
    this.latlng_ = opts.position;
    this.icon_ = opts.icon;
    this.text_ = opts.text || "";
    this.showpop = opts.showpop || false;
    this.popupImgSrc_ = "images/1280.png";
    
    this.updatedPop = false;
	if(document.getElementById('dummyTextNode')) {
		this.dummyTextNode = document.getElementById('dummyTextNode');
	}
	else{
		var dummyTextNode = document.createElement("span");
		dummyTextNode.id = 'dummyTextNode';
		dummyTextNode.style.display = 'none';
		this.map_.getDiv().appendChild(dummyTextNode);
		this.dummyTextNode = dummyTextNode;
		dummyTextNode =null;
	}
	this.setMap(PopupMarker.IS_BAIDU_MAP?this.map_:this.map_._source);
};
 
if(PopupMarker.IS_BAIDU_MAP){
	PopupMarker.prototype.initialize = function(map){
		var spanContainer = document.createElement("span");
	    this.container_ = document.createElement("div");
	    this.iconContainer = document.createElement("div");
	    
	    var panes = this.map_._source.getPanes();
	    panes.floatShadow.appendChild(spanContainer);
		spanContainer.appendChild(this.iconContainer);
		spanContainer.appendChild(this.container_);
		
	    this.iconContainer.style.width = this.ICON_WIDTH + "px";
	    this.iconContainer.style.height = this.ICON_HEIGHT + "px";
	    this.iconContainer.innerHTML = "<img src='" + this.icon_ + "'>";
	    this.iconContainer.style.position = "absolute";
	     
	    this.container_.style.position = "absolute";
	    if (!this.showpop) this.container_.style.visibility = "hidden";
	    this.makeNormalPopup_();
		return spanContainer; 
	};
	PopupMarker.prototype.setMap = function(obj){
		if(obj==null)
			this.map_._source.removeOverlay(this);
		else{
			obj._source.addOverlay(this);
		}
	};
}
PopupMarker.prototype.onAdd = function() {
    this.container_ = document.createElement("div");
    this.iconContainer = document.createElement("div");
    var panes = this.getPanes?this.getPanes():this.map_._source.getPanes();
    panes.floatShadow.appendChild(this.iconContainer);
    panes.floatPane.appendChild(this.container_);
    
    this.iconContainer.style.width = this.ICON_WIDTH + "px";
    this.iconContainer.style.height = this.ICON_HEIGHT + "px";
    this.iconContainer.innerHTML = "<img src='" + this.icon_ + "'>";
    this.iconContainer.style.position = "absolute";

    this.container_.style.position = "absolute";
    if (!this.showpop) this.container_.style.visibility = "hidden";
    this.makeNormalPopup_();
};
PopupMarker.prototype.draw = function() {
    this.redrawNormalPopup_(this.text_);
};
PopupMarker.prototype.onRemove = function() {
	_.dom.remove(this.container_);
	_.dom.remove(this.iconContainer);
};

PopupMarker.prototype.makeNormalPopup_ = function() {
	var frag = document.createDocumentFragment();
	 var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex =100000;
      div.style.backgroundColor = "#EE5D5B";
      div.style.border = "1px solid #BC3B3A";
      div.style.color = "white";
      div.style.height = "18px";
      div.style.padding = "2px";
      div.style.lineHeight = "18px";
      div.style.whiteSpace = "nowrap";
      div.style.MozUserSelect = "none";
      div.style.fontSize = "12px"
      var span = this._span = document.createElement("span");
      div.appendChild(span);
      span.appendChild(document.createTextNode(this._text));      
      var that = this;

      var arrow = this._arrow = document.createElement("div");
      arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
      arrow.style.position = "absolute";
      arrow.style.width = "11px";
      arrow.style.height = "10px";
      arrow.style.top = "57px";
      arrow.style.left = "2px";
      arrow.style.overflow = "hidden";
      div.appendChild(arrow);
     
	
	var bodyContainer_ = div; 
     frag.appendChild(bodyContainer_);
	
	
	this.container_.appendChild(frag);
};
PopupMarker.prototype.redrawNormalPopup_ = function(text) {
    if (this.beforeNormalPopupText_ !== text) {
     
		
			var bodyContainer_ = this.container_.children[0];	
			var _bodyContainer_ = bodyContainer_.children[0];
       		_bodyContainer_.innerHTML = text;
        //if (!_.isIE && text) {
            if (bodyContainer_.firstChild.nodeType === 1) {
                bodyContainer_.firstChild.style.margin = 0;
            }
        //}
       	// var offsetBorder = _.isIE ? 2: 0;
       	var offsetBorder = 2;
        var cSize = this.getHtmlSize_(text);
        
        var rightX = PopupMarker.POPUP_TBL.leftTop.width + cSize.width;
        
        
        bodyContainer_.style.width = cSize.width + "px";
        bodyContainer_.style.height = cSize.height + "px";
        bodyContainer_.style.top = PopupMarker.POPUP_TBL.leftBody.top-20;
        
        this.size_ = {
            "width": (rightX + PopupMarker.POPUP_TBL.rightTop.width),
            "height": (cSize.height + PopupMarker.POPUP_TBL.leftTop.height + PopupMarker.POPUP_TBL.leftBottom.height)
        };
        this.container_.style.width = this.size_.width + "px";
        this.container_.style.height = this.size_.height + "px";
    }
    bodyContainer_ = leftBottom_ = leftBody_ = rightTop_ = rightBottom_ = rightBody_ = centerBottom_ = centerTop_ = null;
 
    this.beforeNormalPopupText_ = text;
};

 
PopupMarker.prototype.getHtmlSize_ = function(html) {
    var size = {};
	this.dummyTextNode.innerHTML = html;
	this.dummyTextNode.style.display = '';
	size.width = this.dummyTextNode.offsetWidth;
	size.height = this.dummyTextNode.offsetHeight;
	this.dummyTextNode.style.display = 'none';
    var ret, lines = html.split(/\n/i), totalSize = new goome.maps.Size(1, 1);
    for (var i = 0; i < lines.length; i++) {
        totalSize.width += size.width;
        totalSize.height += size.height;
    }
	return totalSize;
};
PopupMarker.prototype.makeImgDiv_ = function(imgSrc, params) {
    var imgDiv = document.createElement("div");
    imgDiv.style.position = "absolute";
    imgDiv.style.overflow = "hidden";
    if (params.width) {
        imgDiv.style.width = params.width + "px";
    }
    if (params.height) {
        imgDiv.style.height = params.height + "px";
    }
    var img = null;
    //if (!_.isIE) {
    if (false) {
        img = new Image();
        img.src = imgSrc;

    } else {
        img = document.createElement("div");
        if (params.width) {
            img.style.width = params.width + "px";

        }
        if (params.height) {
            img.style.height = params.height + "px";
        }
    }
    img.style.position = "relative";
    img.style.left = params.left + "px";
    img.style.top = params.top + "px";
    img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + imgSrc + "')";
    imgDiv.appendChild(img);
    img = null;
    return imgDiv;
};
PopupMarker.prototype.fillDiv_ = function(params) {
 

};
PopupMarker.prototype.hide = function() {
    if (this.container_) {
        this.container_.style.visibility = "hidden";
    }
};
PopupMarker.prototype.show = function() {
    if (this.container_) {
        this.container_.style.visibility = "visible";
    }
};
PopupMarker.prototype.toggle = function() {
    if (this.container_) {
        if (this.container_.style.visibility == "hidden") {
            this.show();
        } else {
            this.hide();
        }
    }
};
PopupMarker.prototype.setPosition = function(latlng) {
	if(!latlng._source){
		throw Error('PopupMarker:use wrapped LatLng');
	}
    var pxPos = this.map_.fromLatLngToDivPixel(latlng);
    this.container_.style.left = pxPos.x + "px";
    this.container_.style.top = (pxPos.y - this.size_.height) + "px";
    var icon = this.icon_;
    if(icon.indexOf('east')>-1){
	    this.iconContainer.style.left = (pxPos.x - this.ICON_WIDTH * 0.75) + "px";	
    }else{
	    this.iconContainer.style.left = (pxPos.x - this.ICON_WIDTH / 2) + "px";
    }
    this.iconContainer.style.top = (pxPos.y - this.ICON_HEIGHT) + "px";	
	this.latlng_ = latlng;
};
PopupMarker.prototype.update = function(obj) {
    if ((typeof obj.icon) != "undefined") {
		if(this.icon_!=obj.icon){ 
		}
    }
    if ((typeof obj.position) != "undefined") {
        this.latlng_ = obj.position;
        this.setPosition(this.latlng_);
    }
    if ((typeof obj.text) != "undefined") {
        this.text_ = obj.text;
        this.redrawNormalPopup_(obj.text);

    }
};
PopupMarker.prototype.setZIndex = function(index) {
    this.container_.style.zIndex = index;
    this.iconContainer.style.zIndex = index;
};
PopupMarker.prototype.latlng = function() {
    return this.latlng_;
};
PopupMarker.prototype.hideMarker = function(){
	this.container_.style.visibility = "hidden";
    this.iconContainer.style.visibility = "hidden";
};
PopupMarker.prototype.showMarker = function(){
	this.container_.style.visibility = "visible";
    this.iconContainer.style.visibility = "visible";
};