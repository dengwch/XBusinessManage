﻿/**
 * @license Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For the complete reference:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	
//	config.toolbarGroups = [
//		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
//		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
//		{ name: 'links' },
//		{ name: 'insert' },
//		{ name: 'forms' },
//		{ name: 'tools' },
//		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
//		{ name: 'others' },
//		'/',
//		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
//		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
//		{ name: 'styles' },
//		{ name: 'colors' },
//		{ name: 'about' }
//	];
	
	
	config.toolbar = 'Full'; 
    
    config.toolbar_Full = 
    [ 
        ['Source','-','Save','NewPage','Preview','-','Templates'], 
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'], 
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], 
        ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'], 
        
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
        '/', 
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
        ['Link','Unlink','Anchor'], 
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','About'], 
        '/', 
        ['Styles','Format','Font','FontSize'], 
        ['TextColor','BGColor'], 
        ['Maximize', 'ShowBlocks'] 
    ]; 
	

	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
		config.removeButtons = 'Underline,Subscript,Superscript';

		var pathName = window.document.location.pathname;
	   //获取带"/"的项目名，如：/uimcardprj
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	    config.filebrowserUploadUrl=projectName+"/background/companynew/uploadImg.do";
	    config.filebrowserImageUploadUrl = projectName+'/background/companynew/uploadImg.do'; //固定路径
};
