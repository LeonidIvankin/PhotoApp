package ru.leonidivankin.photovkapp.view;

public interface IRepoListPresenter {
	void bindRepoListRow(int pos, RepoRowView rowView);
	int getRepoCount();
}
