package kr.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.entity.Book;

@Mapper
public interface BookMapper {

	public List<Book> getBookLists();

	public void bookInsert(Book book);

	public void bookDelete(int num);

	public Book bookContent(int num);

	public void bookUpdate(Book book);
	
}
