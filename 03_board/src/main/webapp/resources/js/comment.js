/**
 * 
 */

var make = {
	no : $("#bno").val(),
	id : $("#bid").val(),
	userId : $("#userId").val()
};

function commentList(data) {

	$.ajax({
		url : "commentList.do",
		data : {
			no : make.no
		},
		dataType : "json"
	}).done(makeCommentList);

}

function replyComment() {
	$.ajax({
		url : "replyComment.do",
		data : {
			no : make.no
		}
	}).done(makeReplyList);
}



function replyWrite(result) {


	var html = "";
	html += "<form id='cForm'  action='#'>"
	html += "<tr id='modRow" + result + "'>";
	html += "  <td><textarea name='comment_content' cols='30' rows='4' style='resize: none;'></textarea></td>";
	html += "  <td colspan='2'>";
	html += "    <input type='submit' >";
	html += "    <a href='javascript:commentCancel(" + result
			+ ");'>취소</a>";
	html += "  </td>";
	html += "</tr>";
	html += "<input type='hidden' name='parent_comment' value="+result+">";
	html += "<input type='hidden' name='id' value='${userId}'>";
	html += "<form>"

	$("#row" + result).after(html);
}

$("#cForm").submit(function (result) {
	$.ajax({
		url: "commentWrite.do",
		data: {
			id: make.userId,
			comment_content: $("textarea[name=comment_content").val(),
			parent_comment: $("input[name=parent_comment").val(),
			no: make.no
		},
		dataType: "json"
	}).done(makeCommentList(result));
	console.log(make.no)
});

function makeCommentList(result) {
	var html = "";

	for (var i = 0; i < result.length; i++) {
		var comment = result[i];
		if (comment.parent_comment == 0) {
			html += "<tr id='row" + comment.comment_no + "'>";
			html += "<td id='user'>" + comment.id + "</td>";
			html += "<td id=c_c" + comment.comment_no + ">"
					+ comment.comment_content + "</td>";
			html += "<td>" + comment.comment_reg_date + "</td>";
			html += "<td>" + comment.parent_comment + "</td>";
			
			html += "<td>";
			html += "  <a href='javascript:replyWrite(" + comment.comment_no
					+ ");'>댓글</a>";
			if (make.userId == comment.id) {
				html += "  <a href='javascript:commentDelete("
						+ comment.comment_no + ");'>삭제</a>";
				html += "  <a href='javascript:commentUpdateForm("
						+ comment.comment_no + ");'>수정</a>";
			} else {
				html += "";
			}
			html += "</td>";
			html += "</tr>";
		}
	}
	$("#comment").html(html);
	for (var i = 0; i < result.length; i++) {
		var replyht = "";
		var reply = result[i];
		if (reply.parent_comment != 0) {
			replyht += "<tr>";
			replyht += "<td id='user'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + reply.id + "</td>";
			replyht += "<td id=c_c" + reply.comment_no + ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ reply.comment_content + "</td>";
			replyht += "<td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + reply.comment_reg_date + "</td>";
			replyht += "<td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + reply.parent_comment + "</td>";
			
			replyht += "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";	
			if (make.userId == reply.id) {
				replyht += "  <a href='javascript:commentDelete(" + reply.comment_no
				+ ");'>삭제</a>";
				replyht += "  <a href='javascript:commentUpdateForm("
					+ reply.comment_no + ");'>수정</a>";
			} else {
				replyht += "";
			}
			replyht += "</tr>";
			
		}
		$("#row" + reply.parent_comment).after(replyht)
	}
	if (result.length == 0) {
		html += "<tr>";
		html += "  <td colspan='4'>댓글이 존재하지 않습니다.</td>";
		html += "</tr>";
	}
}

function commentDelete(result) {
	$.ajax({
		url : "commentDelete.do",
		data : {
			comment_no : result
		}
	}).done(commentList);
}

function commentUpdateForm(comment_no) {
	$("#commentList tr[id ^= 'row']").show();
	$("#commentList tr[id ^= 'mod']").hide();

	var modId = $("#row" + comment_no + " > td:eq(0)").text();
	var modContent = $("#row" + comment_no + " > td:eq(1)").text();

	var html = "";
	html += "<tr id='modRow" + comment_no + "'>";
	html += "  <td>" + modId + "</td>";
	html += "  <td><textarea id='comment_content' cols='30' rows='4' readonly='readonly' style='resize: none;'>"
			+ $("#c_c" + comment_no).html() + "</textarea></td>";
	html += "  <td colspan='2'>";
	html += "    <a href='javascript:commentUpdate(" + comment_no
			+ ");'>수정</a>";
	html += "    <a href='javascript:commentCancel(" + comment_no
			+ ");'>취소</a>";
	html += "  </td>";
	html += "</tr>";

	$("#row" + comment_no).after(html);
	$("#row" + comment_no).hide();
}

function commentCancel(comment_no) {
	$("#row" + comment_no).show();
	$("#modRow" + comment_no).hide();

}

$("#rForm").submit(function() {
	$.ajax({
		url : "commentWrite.do",
		data : {
			id : $("input[name=id]").val(),
			no : make.no,
			comment_content : $("textarea[name=comment_content]").val()
		},
		dataType : "json"
	}).done(makeCommentList(result));
	return false;
});
function commentUpdate(comment_no) {
	$.ajax({
		url : "commentUpdate.do",
		data : {
			comment_no : comment_no,
			comment_content : $("#comment_content").val()
		}
	}).done(commentList);
}

commentList();
